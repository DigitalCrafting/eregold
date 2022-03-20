package org.digitalcrafting.eregold.repository.transactions;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransactionsMapper {

    @Select("SELECT nextval('transaction_id_seq')")
    Long getNextId();

    void insert(TransactionEntity entity);
}
