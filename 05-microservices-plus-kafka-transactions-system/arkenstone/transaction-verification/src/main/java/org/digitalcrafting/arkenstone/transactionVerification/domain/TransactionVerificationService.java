package org.digitalcrafting.arkenstone.transactionVerification.domain;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountEntity;
import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountsMapper;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionEntity;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionsMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionVerificationService {
    private final AccountsMapper accountsMapper;
    private final TransactionsMapper transactionsMapper;

    /* Verification steps:
     *  1. get account history
     *  2. exclude transaction to. verify
     *  3. sum all transactions
     *  4. check if sum equals currentBalance of the account
     *  5. check if sum - amountFromTransactionToVerify equals availableBalance
     *  6. if so, change status, update currentBalance, and create dst transaction with status ACCEPTED
     *  7. if not, change status to REJECTED, set availableBalance to currentBalance
     * */
    // TODO With @Transactional annotation, the tests require connection to DB, refactor code to use EntityManager or verify if tests can be run as is
    public void verifyTransaction(Long transactionId, String accountNumber) {
        TransactionEntity transactionToVerify = transactionsMapper.getByPrimaryKey(transactionId, accountNumber);

        if (!TransactionStatusEnum.PENDING.name().equals(transactionToVerify.getStatus())) {
            return;
        }

        AccountEntity accountToVerify = accountsMapper.getByAccountNumber(accountNumber);
        BigDecimal historicallyCalculatedBalance = getHistoryAndCalculateBalance(transactionId, accountNumber);

        if (isTransactionValid(historicallyCalculatedBalance, transactionToVerify, accountToVerify)) {
            acceptTransaction(transactionId, accountNumber, transactionToVerify, accountToVerify);
        } else {
            rejectTransaction(transactionId, accountNumber, accountToVerify);
        }
    }

    private void acceptTransaction(Long transactionId, String accountNumber, TransactionEntity transactionToVerify, AccountEntity accountToVerify) {
        processTransactionForSourceAccount(transactionId, accountNumber, transactionToVerify, accountToVerify);

        AccountEntity dstAccount = accountsMapper.getByAccountNumber(transactionToVerify.getForeignAccountNumber());
        if (dstAccount != null) {
            processTransactionForInternalDestinationAccount(transactionToVerify, dstAccount);
        }
    }

    private void processTransactionForSourceAccount(Long transactionId, String accountNumber, TransactionEntity transactionToVerify, AccountEntity accountToVerify) {
        transactionToVerify.setStatus(TransactionStatusEnum.ACCEPTED.name());
        accountToVerify.setCurrentBalance(accountToVerify.getAvailableBalance());

        transactionsMapper.updateTransactionStatus(transactionId, accountNumber, TransactionStatusEnum.ACCEPTED.name());
        accountsMapper.updateAccountBalance(accountToVerify);
    }

    private void processTransactionForInternalDestinationAccount(TransactionEntity transactionToVerify, AccountEntity dstAccount) {
        TransactionEntity dstTransaction = TransactionConverter.createDstTransaction(transactionToVerify);
        dstAccount.setCurrentBalance(dstAccount.getCurrentBalance().add(dstTransaction.getAmount()));
        dstAccount.setAvailableBalance(dstAccount.getAvailableBalance().add(dstTransaction.getAmount()));

        transactionsMapper.insert(dstTransaction);
        accountsMapper.updateAccountBalance(dstAccount);
    }

    private void rejectTransaction(Long transactionId, String accountNumber, AccountEntity accountToVerify) {
        transactionsMapper.updateTransactionStatus(transactionId, accountNumber, TransactionStatusEnum.REJECTED.name());
        accountToVerify.setAvailableBalance(accountToVerify.getCurrentBalance());
        accountsMapper.updateAccountBalance(accountToVerify);
    }

    /*
     * For transaction to be valid (in our example app):
     * 1. historicallyCalculatedBalance, must be greater than 0, and
     * 2. historicallyCalculatedBalance must be equal to the CurrentBalance
     * 3. historicallyCalculatedBalance minus the transaction amount must be greater than or equal 0 (it's already negative at this stage, so in code, we 'add' it),
     * 4. historicallyCalculatedBalance minus the transaction amount must be equal to availableBalance
     *
     *
     * NOTE: For our simple application we don't need to worry about another transaction modifying the availableBalance before we do the check
     */
    private boolean isTransactionValid(BigDecimal historicallyCalculatedBalance, TransactionEntity transactionToVerify, AccountEntity accountToVerify) {
        return historicallyCalculatedBalance.compareTo(BigDecimal.ZERO) > 0
                && historicallyCalculatedBalance.equals(accountToVerify.getCurrentBalance())
                && historicallyCalculatedBalance.add(transactionToVerify.getAmount()).compareTo(BigDecimal.ZERO) >= 0
                && historicallyCalculatedBalance.add(transactionToVerify.getAmount()).equals(accountToVerify.getAvailableBalance());
    }

    private BigDecimal getHistoryAndCalculateBalance(Long transactionId, String accountNumber) {
        List<TransactionEntity> accountHistory = transactionsMapper.getByAccountNumber(accountNumber);
        return accountHistory
                .stream()
                .filter(transaction -> !transactionId.equals(transaction.getId()))
                .map(TransactionEntity::getAmount)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }
}
