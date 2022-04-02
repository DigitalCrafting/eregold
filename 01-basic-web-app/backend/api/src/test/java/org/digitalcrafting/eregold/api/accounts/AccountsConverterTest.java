package org.digitalcrafting.eregold.api.accounts;

import org.digitalcrafting.eregold.domain.accounts.AccountModel;
import org.digitalcrafting.eregold.domain.accounts.AccountTypeEnum;
import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;
import org.digitalcrafting.eregold.repository.accounts.AccountEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountsConverterTest {

    @Test
    void should_returnNull_when_calledWithNull() {
        // Given
        List<AccountEntity> entityList = null;

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        assertNull(modelList);
    }

    @Test
    void should_returnNull_when_calledWithEmptyList() {
        // Given
        List<AccountEntity> entityList = Collections.emptyList();

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        assertNull(modelList);
    }

    @Test
    void should_returnEmptyList_when_calledWithListOfNull() {
        // Given
        List<AccountEntity> entityList = new ArrayList<>();
        entityList.add(null);

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        assertNotNull(modelList);
        assertTrue(modelList.isEmpty());
    }

    @Test
    void should_returnModelList_when_calledWithEntityList() {
        // Given
        List<AccountEntity> entityList = List.of(
                AccountEntity.builder()
                .type(AccountTypeEnum.DEBIT.name())
                .currentBalance(BigDecimal.ZERO)
                .currency(CurrencyEnum.GLD.name())
                .accountNumber("12ERGD123456")
                .accountName("Test account")
                .build()
        );

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        assertNotNull(modelList);
        assertEquals(1, modelList.size());
        assertEquals(AccountTypeEnum.DEBIT, modelList.get(0).getType());
        assertEquals(CurrencyEnum.GLD, modelList.get(0).getCurrency());
        assertEquals(BigDecimal.ZERO, modelList.get(0).getCurrentBalance());
        assertEquals("12ERGD123456", modelList.get(0).getAccountNumber());
        assertEquals("Test account", modelList.get(0).getAccountName());
    }
}