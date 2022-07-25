package org.digitalcrafting.eregold.api.transactions;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.digitalcrafting.eregold.domain.transactions.TransactionModel;
import org.digitalcrafting.eregold.domain.transactions.TransactionStatusEnum;
import org.digitalcrafting.eregold.domain.transactions.TransactionsConverter;
import org.digitalcrafting.eregold.repository.clients.transactions.MakeTransactionResponse;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionDTO;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionsClient;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionsControllerService {

    private final TransactionsClient transactionsClient;

    @SneakyThrows
    public void transfer(TransactionModel request) {
        Date transactionDate = new Date();
        TransactionDTO srcTransaction = TransactionsConverter.toTransferDTO(request, transactionDate);
        MakeTransactionResponse transactionResponse = this.transactionsClient.make(srcTransaction);

        String transactionStatus = this.transactionsClient.getTransactionStatus(transactionResponse.getId(), transactionResponse.getAccountNumber());

        while (TransactionStatusEnum.PENDING.name().equals(transactionStatus)) {
            log.info("Transction with id {} for account {} is {}", transactionResponse.getId(), transactionResponse.getAccountNumber(), transactionStatus);
            TimeUnit.SECONDS.sleep(5);
            transactionStatus = this.transactionsClient.getTransactionStatus(transactionResponse.getId(), transactionResponse.getAccountNumber());
        }
        log.info("Transction with id {} for account {} is {}", transactionResponse.getId(), transactionResponse.getAccountNumber(), transactionStatus);
    }

    public void deposit(TransactionModel request) {
        Date transactionDate = new Date();
        TransactionDTO depositDTO = TransactionsConverter.toDepositDTO(request, transactionDate);
        this.transactionsClient.make(depositDTO);
    }
}
