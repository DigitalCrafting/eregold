package org.digitalcrafting.eregold.repository.clients.transactions;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "TransactionsClient", url = "${arkenstone.url}/v1/transactions")
public interface TransactionsClient {
    @GetMapping("/{accountNumber}")
    List<TransactionDTO> getByAccountNumber(@PathVariable String accountNumber);

    @PostMapping
    void create(@RequestBody TransactionDTO entity);

    @PostMapping("/multiple")
    void createMultiple(@RequestBody List<TransactionDTO> transactions);
}
