package org.digitalcrafting.arkenstone.transactions.repository.clients.verification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TransactionVerificationClient", url = "${arkenstone.transaction-verification.url}/v1/transaction")
public interface TransactionVerificationClient {
    @GetMapping("/verify/{transactionId}/{accountNumber}")
    void verifyTransaction(@PathVariable Long transactionId, @PathVariable String accountNumber);
}
