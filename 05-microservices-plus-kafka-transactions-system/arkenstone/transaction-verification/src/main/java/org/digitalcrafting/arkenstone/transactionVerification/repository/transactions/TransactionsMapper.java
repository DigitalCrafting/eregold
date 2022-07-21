package org.digitalcrafting.arkenstone.transactionVerification.repository.transactions;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionsMapper {

    @Select("SELECT nextval('transaction_id_seq')")
    Long getNextId();

    void insert(TransactionEntity entity);

    TransactionEntity getByPrimaryKey(@Param("id") Long id, @Param("accountNumber") String accountNumber);

    List<TransactionEntity> getByAccountNumber(String accountNumber);
}
