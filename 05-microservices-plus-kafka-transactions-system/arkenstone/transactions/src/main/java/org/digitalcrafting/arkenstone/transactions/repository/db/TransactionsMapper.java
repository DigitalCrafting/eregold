package org.digitalcrafting.arkenstone.transactions.repository.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionsMapper {

    @Select("SELECT nextval('transaction_id_seq')")
    Long getNextId();

    void insert(TransactionEntity entity);

    List<TransactionEntity> getByAccountNumber(String accountNumber);

    TransactionEntity getByPrimaryKey(@Param("id") Long transactionId, @Param("accountNumber") String accountNumber);
}
