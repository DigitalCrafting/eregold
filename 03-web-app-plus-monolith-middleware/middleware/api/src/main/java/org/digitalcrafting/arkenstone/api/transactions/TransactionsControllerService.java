package org.digitalcrafting.arkenstone.api.transactions;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.domain.transactions.TransactionDTO;
import org.digitalcrafting.arkenstone.domain.transactions.TransactionsConverter;
import org.digitalcrafting.arkenstone.repository.transactions.TransactionEntity;
import org.digitalcrafting.arkenstone.repository.transactions.TransactionsEntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsControllerService {
    private final TransactionsEntityManager entityManager;

    public List<TransactionDTO> getByAccountNumber(String accountNumber) {
        List<TransactionEntity> entityList = entityManager.getByAccountNumber(accountNumber);
        return TransactionsConverter.toDTOList(entityList);
    }

    public void createMultiple(List<TransactionDTO> transactions) {
        List<TransactionEntity> entityList = TransactionsConverter.toEntityList(transactions);
        entityManager.insert(entityList);
    }

    public void create(TransactionDTO transactionDTO) {
        TransactionEntity entity = TransactionsConverter.toEntity(transactionDTO);
        entityManager.insert(entity);
    }
}
