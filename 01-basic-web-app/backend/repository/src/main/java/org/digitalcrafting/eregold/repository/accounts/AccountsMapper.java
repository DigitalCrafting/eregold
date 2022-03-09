package org.digitalcrafting.eregold.repository.accounts;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountsMapper {
    AccountEntity getByAccountNumber(String accountNumber);

    List<AccountEntity> getAccountsForCustomer(String customerId);

    void insertAccount(AccountEntity entity);

    void insertCustomerAccount(String customerId, String accountNumber);
}
