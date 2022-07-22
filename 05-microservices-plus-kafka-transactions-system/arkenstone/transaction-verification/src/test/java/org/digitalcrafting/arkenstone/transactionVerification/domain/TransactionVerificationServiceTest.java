package org.digitalcrafting.arkenstone.transactionVerification.domain;

import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountsMapper;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionVerificationServiceTest {

    @Autowired
    private TransactionVerificationService service;

    @MockBean
    private AccountsMapper accountsMapper;

    @MockBean
    private TransactionsMapper transactionsMapper;

    private TransactionVerificationTestData testDataInstance;

    @Test
    public void should_createService() {
        assertNotNull(service);
    }

    @BeforeEach
    public void setUpTestData() {
        this.testDataInstance = new TransactionVerificationTestData();
    }

    @Test
    public void should_acceptOwnTransfer() {
        // given
        when(accountsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_SOURCE_ACCOUNT);
        when(accountsMapper.getByAccountNumber("12ERGD67890")).thenReturn(testDataInstance.MOCK_DESTINATION_ACCOUNT);
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_CORRECT_OWN_TRANSACTION);
        when(transactionsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_HISTORY_CORRECT_BALANCE);

        // when
        service.verifyTransaction(99L, "12ERGD12345");

        // then
        verify(transactionsMapper, times(1)).updateTransactionStatus(any(), any(), any());
        verify(transactionsMapper, times(1)).insert(any());
        verify(accountsMapper, times(2)).updateAccountBalance(any());
    }

    @Test
    public void should_acceptForeignTransfer() {
        // given
        when(accountsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_SOURCE_ACCOUNT);
        when(accountsMapper.getByAccountNumber("11SRBS111111")).thenReturn(null);
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_CORRECT_FOREIGN_TRANSACTION);
        when(transactionsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_HISTORY_CORRECT_BALANCE);
        // when
        service.verifyTransaction(99L, "12ERGD12345");

        // then
        verify(transactionsMapper, times(1)).updateTransactionStatus(any(), any(), any());
        verify(accountsMapper, times(1)).updateAccountBalance(any());
    }

    @Test
    public void should_return_when_statusIsNotPending() {
        // given
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_TRANSACTION_WRONG_STATUS);

        // when
        service.verifyTransaction(99L, "12ERGD12345");

        // then
        verify(transactionsMapper, times(0)).updateTransactionStatus(any(), any(), any());
        verify(transactionsMapper, times(0)).insert(any());
        verify(accountsMapper, times(0)).updateAccountBalance(any());
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalance_isZero() {
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_CORRECT_OWN_TRANSACTION);
        when(accountsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_SOURCE_ACCOUNT);
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalance_isNot_currentBalance() {
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_CORRECT_OWN_TRANSACTION);
        when(accountsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_SOURCE_ACCOUNT);
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalanceAfterTransaction_isLessThan_Zero() {
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_CORRECT_OWN_TRANSACTION);
        when(accountsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_SOURCE_ACCOUNT);
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalanceAfterTransaction_isNot_AvailableBalance() {
        when(transactionsMapper.getByPrimaryKey(99L, "12ERGD12345")).thenReturn(testDataInstance.MOCK_CORRECT_OWN_TRANSACTION);
        when(accountsMapper.getByAccountNumber("12ERGD12345")).thenReturn(testDataInstance.MOCK_SOURCE_ACCOUNT);
        // TODO
    }
}