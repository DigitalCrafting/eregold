package org.digitalcrafting.eregold.api.transfers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.repository.accounts.AccountEntity;
import org.digitalcrafting.eregold.repository.accounts.AccountsEntityManager;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;
import org.digitalcrafting.eregold.repository.transactions.TransactionsEntityManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransfersControllerService {

    private final TransactionsEntityManager transactionsEntityManager;
    private final AccountsEntityManager accountsEntityManager;

    public void transfer(TransferRequest request) {
        Date transactionDate = new Date();
        TransactionEntity srcTransaction = TransferConverter.convertToSrcTransaction(request, transactionDate);

        if (this.accountsEntityManager.getByAccountNumber(request.getDstAccount()) != null) {
            TransactionEntity dstTransaction = TransferConverter.convertToDstTransaction(request, transactionDate);
            this.transactionsEntityManager.insert(List.of(srcTransaction, dstTransaction));
            updateBalance(srcTransaction);
            updateBalance(dstTransaction);
        } else {
            this.transactionsEntityManager.insert(srcTransaction);
            updateBalance(srcTransaction);
        }
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
    private void updateBalance(TransactionEntity entity) {
        AccountEntity account = accountsEntityManager.getByAccountNumber(entity.getAccountNumber());
        BigDecimal newBalance = account.getCurrentBalance().add(entity.getAmount());
        accountsEntityManager.updateAccountBalance(account.getAccountNumber(), newBalance);
    }
}
