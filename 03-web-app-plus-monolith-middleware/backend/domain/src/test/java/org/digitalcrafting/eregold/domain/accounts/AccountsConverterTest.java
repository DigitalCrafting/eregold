package org.digitalcrafting.eregold.domain.accounts;

import org.digitalcrafting.eregold.repository.clients.accounts.AccountDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AccountsConverterTest {

    @Test
    void should_returnNull_when_calledWithNull() {
        // Given
        List<AccountDTO> entityList = null;

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        Assertions.assertNull(modelList);
    }

    @Test
    void should_returnNull_when_calledWithEmptyList() {
        // Given
        List<AccountDTO> entityList = Collections.emptyList();

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        Assertions.assertNull(modelList);
    }

    @Test
    void should_returnEmptyList_when_calledWithListOfNull() {
        // Given
        List<AccountDTO> entityList = new ArrayList<>();
        entityList.add(null);

        // When
        List<AccountModel> modelList = AccountsConverter.toModelList(entityList);

        // Then
        Assertions.assertNotNull(modelList);
        Assertions.assertTrue(modelList.isEmpty());
    }

    @Test
    void should_returnModelList_when_calledWithEntityList() {
        // Given
        List<AccountDTO> entityList = List.of(
                AccountDTO.builder()
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
        Assertions.assertNotNull(modelList);
        Assertions.assertEquals(1, modelList.size());
        Assertions.assertEquals(AccountTypeEnum.DEBIT, modelList.get(0).getType());
        Assertions.assertEquals(CurrencyEnum.GLD, modelList.get(0).getCurrency());
        Assertions.assertEquals(BigDecimal.ZERO, modelList.get(0).getCurrentBalance());
        Assertions.assertEquals("12ERGD123456", modelList.get(0).getAccountNumber());
        Assertions.assertEquals("Test account", modelList.get(0).getAccountName());
    }
}