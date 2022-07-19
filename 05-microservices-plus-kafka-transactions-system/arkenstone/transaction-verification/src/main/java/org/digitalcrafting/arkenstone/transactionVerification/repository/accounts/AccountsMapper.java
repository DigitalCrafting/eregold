package org.digitalcrafting.arkenstone.transactionVerification.repository.accounts;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountsMapper {
    AccountEntity getByAccountNumber(String accountNumber);

    void insertAccount(AccountEntity entity);

    void updateAccountBalance(@Param("accountNumber") String accountNumber, @Param("balance") BigDecimal balance);
}
