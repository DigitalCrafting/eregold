package org.digitalcrafting.eregold.api.transactions;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.domain.transactions.TransactionModel;
import org.digitalcrafting.eregold.domain.transactions.TransactionsConverter;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionDTO;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionsClient;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransactionsControllerService {

    private final TransactionsClient transactionsClient;

    public void transfer(TransactionModel request) {
        Date transactionDate = new Date();
        TransactionDTO srcTransaction = TransactionsConverter.toTransferDTO(request, transactionDate);
        this.transactionsClient.make(srcTransaction);
    }

    public void deposit(TransactionModel request) {
        Date transactionDate = new Date();
        TransactionDTO depositDTO = TransactionsConverter.toDepositDTO(request, transactionDate);
        this.transactionsClient.make(depositDTO);
    }
}
