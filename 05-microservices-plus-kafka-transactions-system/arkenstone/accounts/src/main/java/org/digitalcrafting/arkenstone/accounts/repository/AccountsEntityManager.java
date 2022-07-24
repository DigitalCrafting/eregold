package org.digitalcrafting.arkenstone.accounts.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsEntityManager {
     private final AccountsMapper accountsMapper;

    public AccountEntity getByAccountNumber(String accountNumber) {
        return accountsMapper.getByAccountNumber(accountNumber);
    }

    public List<AccountEntity> getAccountsForCustomer(String customerId) {
        return accountsMapper.getAccountsForCustomer(customerId);
    }

    @Transactional
    public void createAccount(AccountEntity entity, String customerId) {
        accountsMapper.insertAccount(entity);
        accountsMapper.insertCustomerAccount(customerId, entity.getAccountNumber());
    }

    public void updateAvailableBalance(String accountNumber, BigDecimal balance) {
        accountsMapper.updateAvailableBalance(accountNumber, balance);
    }
}
