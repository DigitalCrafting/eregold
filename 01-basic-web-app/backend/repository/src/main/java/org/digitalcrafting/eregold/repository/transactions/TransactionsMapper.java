package org.digitalcrafting.eregold.repository.transactions;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;

// TODO introduce EntityManager
@Mapper
public interface TransactionsMapper {

    @Select("SELECT nextval('transaction_id_seq')")
    BigInteger getNextId();

    void insert(TransactionEntity entity);
}
