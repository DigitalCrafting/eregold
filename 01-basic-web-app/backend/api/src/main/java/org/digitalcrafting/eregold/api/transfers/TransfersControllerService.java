package org.digitalcrafting.eregold.api.transfers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.repository.accounts.AccountsEntityManager;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;
import org.digitalcrafting.eregold.repository.transactions.TransactionsMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransfersControllerService {

    private final TransactionsMapper transactionsMapper;
    private final AccountsEntityManager accountsEntityManager;

    public void transfer(TransferRequest request) {
        /*
        * TODO
        * When making a transfer:
        * - srcTransaction is always created, and srcAccount balance should be updated
        * - dstTransaction is created if the transfer is inside the bank, meaning: if the dstAccount exists in Eregold,
        *   and dstAccount balance is updated
        *
        * - create service that handles the update of balances
        * */
        Date transactionDate = new Date();
        TransactionEntity srcTransaction = TransferConverter.convertToSrcTransaction(request, transactionDate);
        TransactionEntity dstTransaction = TransferConverter.convertToDstTransaction(request, transactionDate);
    }
}
