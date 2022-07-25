package org.digitalcrafting.arkenstone.transactions.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactions.domain.MakeTransactionResponse;
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

    @GetMapping("/{transactionId}/{accountNumber}")
    public String getTransactionStatus(@PathVariable Long transactionId, @PathVariable String accountNumber) {
        return service.getTransactionStatus(transactionId, accountNumber);
    }

    @PostMapping
    public MakeTransactionResponse make(@RequestBody TransactionDTO transactionDto) {
        return service.make(transactionDto);
    }
}
