package org.digitalcrafting.arkenstone.transactions.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.transactions.domain.*;
import org.digitalcrafting.arkenstone.transactions.repository.clients.AccountsClient;
import org.digitalcrafting.arkenstone.transactions.repository.db.TransactionEntity;
import org.digitalcrafting.arkenstone.transactions.repository.db.TransactionsEntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionsControllerService {
    private final AccountsClient accountsClient;
    private final TransactionsEntityManager entityManager;
    private final KafkaTemplate<String, KafkaTransactionMessage> kafkaTemplate;

    public List<TransactionDTO> getByAccountNumber(String accountNumber) {
        List<TransactionEntity> entityList = entityManager.getByAccountNumber(accountNumber);
        return TransactionsConverter.toDTOList(entityList);
    }

    public String getTransactionStatus(Long transactionId, String accountNumber) {
        return entityManager.getByPrimaryKey(transactionId, accountNumber).getStatus();
    }

    public MakeTransactionResponse make(TransactionDTO transactionDTO) {
        if (TransactionTypeEnum.DEPOSIT.equals(transactionDTO.getType())) {
            return makeDeposit(transactionDTO);
        } else if (TransactionTypeEnum.TRANSFER.equals(transactionDTO.getType())) {
            return makeTransfer(transactionDTO);
        } else {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    private MakeTransactionResponse makeDeposit(TransactionDTO transactionDTO) {
        TransactionEntity entity = TransactionsConverter.toDepositEntity(transactionDTO);
        Long id = entityManager.insertAndGetId(entity);
        accountsClient.updateAvailableBalance(entity.getAccountNumber(), entity.getAmount());
        return new MakeTransactionResponse(id, entity.getAccountNumber());
    }

    private MakeTransactionResponse makeTransfer(TransactionDTO transactionDTO) {
        TransactionEntity entity = TransactionsConverter.toTransferEntity(transactionDTO);
        Long id = entityManager.insertAndGetId(entity);
        accountsClient.updateAvailableBalance(entity.getAccountNumber(), entity.getAmount());
        kafkaTemplate.send("transaction-verification", UUID.randomUUID().toString(), new KafkaTransactionMessage(id, entity.getAccountNumber()));
        return new MakeTransactionResponse(id, entity.getAccountNumber());
    }
}
