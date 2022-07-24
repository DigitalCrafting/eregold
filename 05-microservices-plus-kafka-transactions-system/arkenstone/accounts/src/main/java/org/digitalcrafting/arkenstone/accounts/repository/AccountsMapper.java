package org.digitalcrafting.arkenstone.accounts.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AccountsMapper {
    AccountEntity getByAccountNumber(String accountNumber);

    List<AccountEntity> getAccountsForCustomer(String customerId);

    void insertAccount(AccountEntity entity);

    void insertCustomerAccount(String customerId, String accountNumber);

    void updateAvailableBalance(@Param("accountNumber") String accountNumber, @Param("balance") BigDecimal balance);
}
