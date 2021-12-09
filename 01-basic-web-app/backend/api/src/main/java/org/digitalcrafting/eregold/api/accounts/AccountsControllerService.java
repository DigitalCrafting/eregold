package org.digitalcrafting.eregold.api.accounts;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.authentication.EregoldSessionContext;
import org.digitalcrafting.eregold.domain.accounts.AccountModel;
import org.digitalcrafting.eregold.repository.accounts.AccountEntity;
import org.digitalcrafting.eregold.repository.accounts.AccountsEntityManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsControllerService {

    private final AccountsEntityManager accountsEntityManager;
    private final EregoldSessionContext sessionContext;

    public List<AccountModel> getAccounts() {
        List<AccountEntity> entityList = accountsEntityManager.getAccountsForCustomer(sessionContext.getCustomerId());
        return AccountsConverter.convert(entityList);
    }

    public void createAccount(CreateAccountRequest request) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(generateAccountNumber());
        accountEntity.setType(request.getAccountType().name());
        accountEntity.setCurrency("GLD");
        accountEntity.setCurrentBalance(BigDecimal.ZERO);
        accountsEntityManager.createAccount(accountEntity, sessionContext.getCustomerId());
    }

    private String generateAccountNumber() {
        StringBuilder number;
        do {
            number = new StringBuilder("12ERGD");
            // toEpochMilli should return number of length 13 at the time of writing this code, so we want to limit ourselves to 12
            number.append(String.valueOf(Instant.now().toEpochMilli()).substring(0,12));
        } while (accountsEntityManager.getByAccountNumber(number.toString()) != null);
        return number.toString();
    }
}
