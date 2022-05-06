package org.digitalcrafting.arkenstone.transactions.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactions.domain.TransactionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transactions")
public class TransactionsController {
    private final TransactionsControllerService service;

    @GetMapping("/{accountNumber}")
    public List<TransactionDTO> getByAccountNumber(@PathVariable String accountNumber) {
        return service.getByAccountNumber(accountNumber);
    }

    @PostMapping("/multiple")
    public void createMultiple(@RequestBody List<TransactionDTO> transactions) {
        service.createMultiple(transactions);
    }

    @PostMapping
    public void create(@RequestBody TransactionDTO transactionDto) {
        service.create(transactionDto);
    }
}
