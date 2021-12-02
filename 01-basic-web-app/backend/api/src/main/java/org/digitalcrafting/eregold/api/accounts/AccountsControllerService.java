package org.digitalcrafting.eregold.api.accounts;

import org.digitalcrafting.eregold.domain.accounts.AccountModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountsControllerService {
    public List<AccountModel> getAccounts() {
        AccountModel account = AccountModel.builder()
                .accountNumber("12ERGD213792136123")
                .currency("GLD")
                .currentBalance(1200.00)
                .type("Debit")
                .build();
        return List.of(account);
    }
}
