package org.digitalcrafting.eregold.domain.transactions;

import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class TransactionsConverterTest {
    private TransactionModel mockTransferRequest = TransactionModel.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .description("Test transfer")
            .srcAccount("12ERGD12345")
            .dstAccount("12ERGD67890")
            .build();
    private TransactionModel mockDepositRequest = TransactionModel.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .description("Deposit")
            .dstAccount("12ERGD67890")
            .build();
    private Date currentDate = new Date();

    @Test
    void should_returnNull_when_calledWithNull() {
        // Given
        List<TransactionDTO> entityList = null;

        // When
        List<TransactionHistoryModel> modelList = TransactionsConverter.toModelList(entityList);

        // Then
        Assertions.assertNull(modelList);
    }

    @Test
    void should_returnNull_when_calledWithEmptyList() {
        // Given
        List<TransactionDTO> entityList = Collections.emptyList();

        // When
        List<TransactionHistoryModel> modelList = TransactionsConverter.toModelList(entityList);

        // Then
        Assertions.assertNull(modelList);
    }

    @Test
    void should_returnEmptyList_when_calledWithListOfNull() {
        // Given
        List<TransactionDTO> entityList = new ArrayList<>();
        entityList.add(null);

        // When
        List<TransactionHistoryModel> modelList = TransactionsConverter.toModelList(entityList);

        // Then
        Assertions.assertNotNull(modelList);
        Assertions.assertTrue(modelList.isEmpty());
    }

    @Test
    void should_returnModelList_when_calledWithEntityList() {
        // Given
        Date transactionDate = new Date();
        List<TransactionDTO> entityList = List.of(
                TransactionDTO.builder()
                        .accountNumber("12ERGD12345")
                        .foreignAccountNumber("12ERGD67890")
                        .currency(CurrencyEnum.GLD.name())
                        .amount(BigDecimal.TEN)
                        .date(transactionDate)
                        .id(1l)
                        .type(TransactionTypeEnum.TRANSFER.name())
                        .status(TransactionStatusEnum.PENDING.name())
                        .description("Test")
                        .build()
        );

        // When
        List<TransactionHistoryModel> modelList = TransactionsConverter.toModelList(entityList);

        // Then
        Assertions.assertNotNull(modelList);
        Assertions.assertEquals(1, modelList.size());

        Assertions.assertEquals(1l, modelList.get(0).getId());
        Assertions.assertEquals("12ERGD12345", modelList.get(0).getAccountNumber());
        Assertions.assertEquals("12ERGD67890", modelList.get(0).getForeignAccountNumber());
        Assertions.assertEquals("Test", modelList.get(0).getDescription());
        Assertions.assertEquals(TransactionTypeEnum.TRANSFER, modelList.get(0).getType());
        Assertions.assertEquals(CurrencyEnum.GLD, modelList.get(0).getCurrency());
        Assertions.assertEquals(TransactionStatusEnum.PENDING, modelList.get(0).getStatus());
        Assertions.assertEquals(BigDecimal.TEN, modelList.get(0).getAmount());
        Assertions.assertEquals(transactionDate, modelList.get(0).getDate());

    }

    @Test
    void should_convertToSrcTransferEntity() {
        // When
        TransactionDTO srcTransactionDTO = TransactionsConverter.toSrcTransferDTO(mockTransferRequest, currentDate);

        // Then
        Assertions.assertNotNull(srcTransactionDTO);
        Assertions.assertEquals(currentDate, srcTransactionDTO.getDate());
        Assertions.assertEquals(BigDecimal.TEN.negate(), srcTransactionDTO.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), srcTransactionDTO.getCurrency());
        Assertions.assertEquals(TransactionTypeEnum.TRANSFER.name(), srcTransactionDTO.getType());
        Assertions.assertEquals("Test transfer", srcTransactionDTO.getDescription());
        Assertions.assertEquals("12ERGD12345", srcTransactionDTO.getAccountNumber());
        Assertions.assertEquals("12ERGD67890", srcTransactionDTO.getForeignAccountNumber());
    }

    @Test
    void should_convertToDstTransferEntity() {
        // When
        TransactionDTO dstTransactionDTO = TransactionsConverter.toDstTransferDTO(mockTransferRequest, currentDate);

        // Then
        Assertions.assertNotNull(dstTransactionDTO);
        Assertions.assertEquals(currentDate, dstTransactionDTO.getDate());
        Assertions.assertEquals(BigDecimal.TEN, dstTransactionDTO.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), dstTransactionDTO.getCurrency());
        Assertions.assertEquals(TransactionTypeEnum.TRANSFER.name(), dstTransactionDTO.getType());
        Assertions.assertEquals("Test transfer", dstTransactionDTO.getDescription());
        Assertions.assertEquals("12ERGD12345", dstTransactionDTO.getForeignAccountNumber());
        Assertions.assertEquals("12ERGD67890", dstTransactionDTO.getAccountNumber());
    }

    @Test
    void should_convertToDepositEntity() {
        // When
        TransactionDTO depositEntity = TransactionsConverter.toDepositDTO(mockDepositRequest, currentDate);

        // Then
        Assertions.assertNotNull(depositEntity);
        Assertions.assertEquals(currentDate, depositEntity.getDate());
        Assertions.assertEquals(BigDecimal.TEN, depositEntity.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), depositEntity.getCurrency());
        Assertions.assertEquals(TransactionTypeEnum.DEPOSIT.name(), depositEntity.getType());
        Assertions.assertEquals("Deposit", depositEntity.getDescription());
        Assertions.assertEquals("12ERGD67890", depositEntity.getAccountNumber());
    }
}