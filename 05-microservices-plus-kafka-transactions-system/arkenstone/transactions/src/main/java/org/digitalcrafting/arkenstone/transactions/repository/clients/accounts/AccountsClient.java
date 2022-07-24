package org.digitalcrafting.arkenstone.transactions.repository.clients.accounts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient(value = "AccountsClient", url = "${arkenstone.gateway.url}/v1/accounts")
public interface AccountsClient {

    /*
     *  TODO this should only update availableBalance.
     *  Actual balance will be update by TransactionVerificationService, after transaction is accepted.
     */
    @PostMapping("/{accountNumber}/balance/available")
    void updateAvailableBalance(@PathVariable String accountNumber, @RequestBody BigDecimal amount);

    @GetMapping("/{accountNumber}")
    AccountDTO getByAccountNumber(@PathVariable String accountNumber);
}
