package org.digitalcrafting.eregold.domain.transfers;

import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

class TransferConverterTest {
    private TransactionModel mockRequest = TransactionModel.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .description("Test transfer")
            .srcAccount("12ERGD12345")
            .dstAccount("12ERGD67890")
            .build();
    private Date currentDate = new Date();


    @Test
    void should_convertToSrcTransaction() {
        // When
        TransactionEntity srcTransactionEntity = TransferConverter.toSrcTransactionEntity(mockRequest, currentDate);

        // Then
        Assertions.assertNotNull(srcTransactionEntity);
        Assertions.assertEquals(currentDate, srcTransactionEntity.getDate());
        Assertions.assertEquals(BigDecimal.TEN.negate(), srcTransactionEntity.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), srcTransactionEntity.getCurrency());
        Assertions.assertEquals("Test transfer", srcTransactionEntity.getDescription());
        Assertions.assertEquals("12ERGD12345", srcTransactionEntity.getAccountNumber());
        Assertions.assertEquals("12ERGD67890", srcTransactionEntity.getForeignAccountNumber());
    }

    @Test
    void should_convertToDstTransaction() {
        // When
        TransactionEntity dstTransactionEntity = TransferConverter.toDstTransactionEntity(mockRequest, currentDate);

        // Then
        Assertions.assertNotNull(dstTransactionEntity);
        Assertions.assertEquals(currentDate, dstTransactionEntity.getDate());
        Assertions.assertEquals(BigDecimal.TEN, dstTransactionEntity.getAmount());
        Assertions.assertEquals(CurrencyEnum.GLD.name(), dstTransactionEntity.getCurrency());
        Assertions.assertEquals("Test transfer", dstTransactionEntity.getDescription());
        Assertions.assertEquals("12ERGD12345", dstTransactionEntity.getForeignAccountNumber());
        Assertions.assertEquals("12ERGD67890", dstTransactionEntity.getAccountNumber());
    }
}