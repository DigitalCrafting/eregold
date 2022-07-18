package org.digitalcrafting.eregold.repository.clients.accounts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "AccountsClient", url = "${arkenstone.url}/v1/accounts")
public interface AccountsClient {
    @GetMapping("/{accountNumber}")
    AccountDTO getByAccountNumber(@PathVariable String accountNumber);

    @GetMapping("/list/{customerId}")
    List<AccountDTO> getAccountsForCustomer(@PathVariable String customerId);

    @PostMapping("/{customerId}")
    void createAccount(@RequestBody AccountDTO entity, @PathVariable String customerId);

    @PostMapping("/{accountNumber}/update")
    void updateAccountBalance(@PathVariable String accountNumber, @RequestBody BigDecimal amount);
}
