package org.digitalcrafting.arkenstone.api.accounts;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.domain.accounts.AccountDTO;
import org.digitalcrafting.arkenstone.domain.accounts.AccountsConverter;
import org.digitalcrafting.arkenstone.repository.accounts.AccountEntity;
import org.digitalcrafting.arkenstone.repository.accounts.AccountsEntityManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsControllerService {

    private final AccountsEntityManager entityManager;

    public AccountDTO getByAccountNumber(String accountNumber) {
        AccountEntity entity = entityManager.getByAccountNumber(accountNumber);
        return AccountsConverter.toDTO(entity);
    }

    public List<AccountDTO> getAccountsForCustomer(String customerId) {
        List<AccountEntity> entityList = entityManager.getAccountsForCustomer(customerId);
        return AccountsConverter.toDTOList(entityList);
    }

    public void createAccount(AccountDTO accountDTO, String customerId) {
        AccountEntity entity = AccountsConverter.toEntity(accountDTO);
        entityManager.createAccount(entity, customerId);
    }

    public void updateAccountBalance(String accountNumber, BigDecimal balance) {
        entityManager.updateAccountBalance(accountNumber, balance);
    }
}
