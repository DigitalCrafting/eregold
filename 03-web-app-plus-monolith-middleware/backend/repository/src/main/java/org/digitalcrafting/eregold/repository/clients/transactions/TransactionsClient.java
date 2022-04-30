package org.digitalcrafting.eregold.repository.clients.transactions;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/* TODO Implement Feign client */
@FeignClient
public interface TransactionsClient {
    void insert(List<TransactionDTO> transactions);

    void insert(TransactionDTO entity);

    List<TransactionDTO> getByAccountNumber(String accountNumber);
}
