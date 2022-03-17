package org.digitalcrafting.eregold.repository.transactions;

import org.apache.ibatis.annotations.Mapper;

// TODO introduce EntityManager
@Mapper
public interface TransactionsMapper {
    void insert(TransactionEntity entity);
}
