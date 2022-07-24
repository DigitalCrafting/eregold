package org.digitalcrafting.arkenstone.transactions.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactions.domain.TransactionDTO;
import org.digitalcrafting.arkenstone.transactions.domain.TransactionTypeEnum;
import org.digitalcrafting.arkenstone.transactions.domain.TransactionsConverter;
import org.digitalcrafting.arkenstone.transactions.repository.clients.accounts.AccountsClient;
import org.digitalcrafting.arkenstone.transactions.repository.clients.verification.TransactionVerificationClient;
import org.digitalcrafting.arkenstone.transactions.repository.db.TransactionEntity;
import org.digitalcrafting.arkenstone.transactions.repository.db.TransactionsEntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsControllerService {
    private final AccountsClient accountsClient;
    private final TransactionsEntityManager entityManager;
    private final TransactionVerificationClient verificationClient;

    public List<TransactionDTO> getByAccountNumber(String accountNumber) {
        List<TransactionEntity> entityList = entityManager.getByAccountNumber(accountNumber);
        return TransactionsConverter.toDTOList(entityList);
    }

    public void make(TransactionDTO transactionDTO) {
        if (TransactionTypeEnum.DEPOSIT.equals(transactionDTO.getType())) {
            makeDeposit(transactionDTO);
        } else if (TransactionTypeEnum.TRANSFER.equals(transactionDTO.getType())) {
            makeTransfer(transactionDTO);
        }
    }

    private void makeDeposit(TransactionDTO transactionDTO) {
        TransactionEntity entity = TransactionsConverter.toDepositEntity(transactionDTO);
        entityManager.insert(entity);
        accountsClient.updateAvailableBalance(entity.getAccountNumber(), entity.getAmount());
    }

    private void makeTransfer(TransactionDTO transactionDTO) {
        TransactionEntity entity = TransactionsConverter.toTransferEntity(transactionDTO);
        Long id = entityManager.insertAndGetId(entity);
        accountsClient.updateAvailableBalance(entity.getAccountNumber(), entity.getAmount());
        verificationClient.verifyTransaction(id, entity.getAccountNumber());
    }
}
