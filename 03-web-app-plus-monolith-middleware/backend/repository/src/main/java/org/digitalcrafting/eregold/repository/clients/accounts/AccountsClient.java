package org.digitalcrafting.eregold.repository.clients.accounts;

import org.springframework.cloud.openfeign.FeignClient;

import java.math.BigDecimal;
import java.util.List;

/* TODO Implement Feign client */
@FeignClient
public interface AccountsClient {
    AccountDTO getByAccountNumber(String accountNumber);

    List<AccountDTO> getAccountsForCustomer(String customerId);

    void createAccount(AccountDTO entity, String customerId);

    void updateAccountBalance(String accountNumber, BigDecimal balance);
}
