package org.digitalcrafting.eregold.api.transfers;

import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;
import org.digitalcrafting.eregold.repository.transactions.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransferConverterTest {
    private TransferRequest mockRequest = TransferRequest.builder()
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
        TransactionEntity srcTransactionEntity = TransferConverter.convertToSrcTransaction(mockRequest, currentDate);

        // Then
        assertNotNull(srcTransactionEntity);
        assertEquals(currentDate, srcTransactionEntity.getDate());
        assertEquals(BigDecimal.TEN.negate(), srcTransactionEntity.getAmount());
        assertEquals(CurrencyEnum.GLD.name(), srcTransactionEntity.getCurrency());
        assertEquals("Test transfer", srcTransactionEntity.getDescription());
        assertEquals("12ERGD12345", srcTransactionEntity.getAccountNumber());
        assertEquals("12ERGD67890", srcTransactionEntity.getDstAccountNumber());
    }

    @Test
    void should_convertToDstTransaction() {
        // When
        TransactionEntity dstTransactionEntity = TransferConverter.convertToDstTransaction(mockRequest, currentDate);

        // Then
        assertNotNull(dstTransactionEntity);
        assertEquals(currentDate, dstTransactionEntity.getDate());
        assertEquals(BigDecimal.TEN, dstTransactionEntity.getAmount());
        assertEquals(CurrencyEnum.GLD.name(), dstTransactionEntity.getCurrency());
        assertEquals("Test transfer", dstTransactionEntity.getDescription());
        assertEquals("12ERGD12345", dstTransactionEntity.getDstAccountNumber());
        assertEquals("12ERGD67890", dstTransactionEntity.getAccountNumber());
    }
}