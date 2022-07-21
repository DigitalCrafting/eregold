package org.digitalcrafting.arkenstone.transactionVerification.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactionVerification.domain.TransactionVerificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transaction")
public class VerificationController {

    private final TransactionVerificationService service;

    @GetMapping("/verify/{transactionId}/{accountNumber}")
    public void verifyTransaction(@PathVariable Long transactionId, @PathVariable String accountNumber) {
        service.verifyTransaction(transactionId, accountNumber);
    }
}
