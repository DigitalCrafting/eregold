package org.digitalcrafting.arkenstone.transactions.domain;

import org.digitalcrafting.arkenstone.transactions.repository.db.TransactionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

class TransactionsConverterTest {
    private static Date currentDate = new Date();
    private TransactionDTO mockTransferDTO = TransactionDTO.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .type(TransactionTypeEnum.TRANSFER)
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("12ERGD67890")
            .date(currentDate)
            .build();

    private TransactionDTO mockDepositDTO = TransactionDTO.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .type(TransactionTypeEnum.DEPOSIT)
            .description("Deposit")
            .accountNumber("12ERGD12345")
            .date(currentDate)
            .build();


    @Test
    void should_convertToDepositEntity() {
        // When
        TransactionEntity dstTransactionEntity = TransactionsConverter.toDepositEntity(mockDepositDTO);

        // Then
        Assertions.assertNotNull(dstTransactionEntity);
        Assertions.assertEquals(currentDate, dstTransactionEntity.getDate());
        Assertions.assertEquals(BigDecimal.TEN, dstTransactionEntity.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), dstTransactionEntity.getCurrency());
        Assertions.assertEquals(TransactionTypeEnum.DEPOSIT.name(), dstTransactionEntity.getType());
        Assertions.assertEquals(TransactionStatusEnum.ACCEPTED.name(), dstTransactionEntity.getStatus());
        Assertions.assertEquals("Deposit", dstTransactionEntity.getDescription());
        Assertions.assertEquals("12ERGD12345", dstTransactionEntity.getAccountNumber());
    }


    @Test
    void should_convertToTransferEntity() {
        // When
        TransactionEntity transferEntity = TransactionsConverter.toTransferEntity(mockTransferDTO);

        // Then
        Assertions.assertNotNull(transferEntity);
        Assertions.assertEquals(currentDate, transferEntity.getDate());
        Assertions.assertEquals(BigDecimal.TEN.negate(), transferEntity.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), transferEntity.getCurrency());
        Assertions.assertEquals(TransactionTypeEnum.TRANSFER.name(), transferEntity.getType());
        Assertions.assertEquals(TransactionStatusEnum.PENDING.name(), transferEntity.getStatus());
        Assertions.assertEquals("Test transfer", transferEntity.getDescription());
        Assertions.assertEquals("12ERGD12345", transferEntity.getAccountNumber());
        Assertions.assertEquals("12ERGD67890", transferEntity.getForeignAccountNumber());
    }
}