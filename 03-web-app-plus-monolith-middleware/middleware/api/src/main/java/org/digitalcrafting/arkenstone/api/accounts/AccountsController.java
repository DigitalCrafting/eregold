package org.digitalcrafting.arkenstone.api.accounts;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.domain.accounts.AccountDTO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/accounts")
public class AccountsController {
    private final AccountsControllerService service;

    @GetMapping("/{accountNumber}")
    public AccountDTO getByAccountNumber(@PathVariable String accountNumber) {
        return service.getByAccountNumber(accountNumber);
    }

    @GetMapping("/list/{customerId}")
    public List<AccountDTO> getAccountsForCustomer(@PathVariable String customerId) {
        return service.getAccountsForCustomer(customerId);
    }

    @PostMapping("/{customerId}")
    public void createAccount(@RequestBody AccountDTO accountDTO, @PathVariable String customerId) {
        service.createAccount(accountDTO, customerId);
    }

    @PostMapping("/{accountNumber}/update")
    public void updateAccountBalance(@PathVariable String accountNumber, @RequestBody BigDecimal balance) {
        service.updateAccountBalance(accountNumber, balance);
    }
}
