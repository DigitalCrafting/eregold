package org.digitalcrafting.arkenstone.api.transactions;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.domain.transactions.TransactionDTO;
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
