package org.digitalcrafting.arkenstone.transactions.repository.db;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactions.repository.clients.accounts.AccountsClient;
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

    @Transactional
    public void insert(TransactionEntity entity) {
        Long transactionId = mapper.getNextId();
        entity.setId(transactionId);
        this.mapper.insert(entity);
    }

    public List<TransactionEntity> getByAccountNumber(String accountNumber) {
        return this.mapper.getByAccountNumber(accountNumber);
    }
}
