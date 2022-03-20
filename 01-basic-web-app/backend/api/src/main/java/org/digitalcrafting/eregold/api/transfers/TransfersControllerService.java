package org.digitalcrafting.eregold.api.transfers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.repository.accounts.AccountsEntityManager;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;
import org.digitalcrafting.eregold.repository.transactions.TransactionsEntityManager;
import org.springframework.stereotype.Service;

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
        } else {
            this.transactionsEntityManager.insert(srcTransaction);
        }
    }
}
