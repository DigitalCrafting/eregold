package org.digitalcrafting.eregold.api.transactions;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.domain.transactions.TransactionModel;
import org.digitalcrafting.eregold.domain.transactions.TransactionsConverter;
import org.digitalcrafting.eregold.repository.clients.accounts.AccountDTO;
import org.digitalcrafting.eregold.repository.clients.accounts.AccountsClient;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionDTO;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionsClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsControllerService {

    private final TransactionsClient transactionsClient;
    private final AccountsClient accountsClient;

    public void transfer(TransactionModel request) {
        Date transactionDate = new Date();
        TransactionDTO srcTransaction = TransactionsConverter.toSrcTransferDTO(request, transactionDate);

        if (this.accountsClient.getByAccountNumber(request.getDstAccount()) != null) {
            TransactionDTO dstTransaction = TransactionsConverter.toDstTransferDTO(request, transactionDate);
            this.transactionsClient.createMultiple(List.of(srcTransaction, dstTransaction));
            updateBalance(srcTransaction);
            updateBalance(dstTransaction);
        } else {
            this.transactionsClient.create(srcTransaction);
            updateBalance(srcTransaction);
        }
    }

    public void deposit(TransactionModel request) {
        Date transactionDate = new Date();
        TransactionDTO depositDTO = TransactionsConverter.toDepositDTO(request, transactionDate);
        this.transactionsClient.create(depositDTO);
        updateBalance(depositDTO);
    }

    /* TODO change logic to update balance based on all transactions from day 1, after you create deposit logic
    * Balance should be calculated based on all transactions, where the first transaction will be incoming one,
    * either by depositing your own money or by transfer from external/other account.
    *
    * In case of performance issues, because of large number of transactions, baseline should be established,
    * with a specified date of its creation.
    *
    * Baseline is just a balance calculated by summing up amounts of transactions sorted by date, up to a certain date.
    * */
    private void updateBalance(TransactionDTO dto) {
        AccountDTO account = accountsClient.getByAccountNumber(dto.getAccountNumber());
        BigDecimal newBalance = account.getCurrentBalance().add(dto.getAmount());
        accountsClient.updateAccountBalance(account.getAccountNumber(), newBalance);
    }
}
