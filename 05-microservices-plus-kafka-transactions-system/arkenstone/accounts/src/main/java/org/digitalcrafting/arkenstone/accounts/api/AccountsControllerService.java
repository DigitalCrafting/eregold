package org.digitalcrafting.arkenstone.accounts.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.accounts.domain.AccountDTO;
import org.digitalcrafting.arkenstone.accounts.domain.AccountsConverter;
import org.digitalcrafting.arkenstone.accounts.repository.AccountEntity;
import org.digitalcrafting.arkenstone.accounts.repository.AccountsEntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

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

    /* TODO this should actually just update availableBalance, when the Kafka transaction system is implemented */
    public void updateAvailableBalance(String accountNumber, BigDecimal amount) {
        AccountEntity entity = entityManager.getByAccountNumber(accountNumber);
        if (entity != null) {
            BigDecimal oldBalance = entity.getAvailableBalance();
            entityManager.updateAvailableBalance(accountNumber, oldBalance.add(amount));
        } else {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Account doesn't exist");
        }
    }
}
