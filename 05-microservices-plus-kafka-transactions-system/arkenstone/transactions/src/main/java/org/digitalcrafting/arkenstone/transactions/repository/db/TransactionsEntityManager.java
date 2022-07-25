package org.digitalcrafting.arkenstone.transactions.repository.db;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactions.repository.clients.AccountsClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsEntityManager {
    private final TransactionsMapper mapper;
    private final AccountsClient client;

    @Transactional
    public Long insertAndGetId(TransactionEntity entity) {
        Long id = mapper.getNextId();
        entity.setId(id);
        mapper.insert(entity);
        return id;
    }

    public List<TransactionEntity> getByAccountNumber(String accountNumber) {
        return this.mapper.getByAccountNumber(accountNumber);
    }

    public TransactionEntity getByPrimaryKey(Long transactionId, String accountNumber) {
        return mapper.getByPrimaryKey(transactionId, accountNumber);
    }
}
