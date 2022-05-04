package org.digitalcrafting.eregold.api.transactions;

import org.digitalcrafting.eregold.domain.accounts.CurrencyEnum;
import org.digitalcrafting.eregold.domain.transactions.TransactionModel;
import org.digitalcrafting.eregold.domain.transactions.TransactionTypeEnum;
import org.digitalcrafting.eregold.domain.transactions.TransactionsConverter;
import org.digitalcrafting.eregold.repository.clients.accounts.AccountDTO;
import org.digitalcrafting.eregold.repository.clients.accounts.AccountsClient;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionDTO;
import org.digitalcrafting.eregold.repository.clients.transactions.TransactionsClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionsControllerServiceTest {
    @Autowired
    private TransactionsControllerService service;

    @MockBean
    private TransactionsClient transactionsClient;

    @MockBean
    private AccountsClient accountsClient;

    private TransactionModel mockTransferRequest = TransactionModel.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .description("Test transfer")
            .srcAccount("12ERGD12345")
            .dstAccount("12ERGD67890")
            .build();
    private TransactionDTO mockSrcTransferEntity = TransactionDTO.builder()
            .amount(BigDecimal.TEN.negate())
            .currency(CurrencyEnum.GLD.name())
            .type(TransactionTypeEnum.TRANSFER.name())
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .build();
    private TransactionDTO mockDstTransferEntity = TransactionDTO.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD.name())
            .type(TransactionTypeEnum.TRANSFER.name())
            .description("Test transfer")
            .accountNumber("12ERGD67890")
            .build();
    private AccountDTO mockSrcAccountDTO = AccountDTO.builder()
            .accountName("Src account")
            .accountNumber("12ERGD12345")
            .currentBalance(new BigDecimal("1000"))
            .currency(CurrencyEnum.GLD.name())
            .build();
    private AccountDTO mockDstAccountDTO = AccountDTO.builder()
            .accountName("Dst account")
            .accountNumber("12ERGD67890")
            .currentBalance(new BigDecimal("1000"))
            .currency(CurrencyEnum.GLD.name())
            .build();

    private TransactionModel mockDepositRequest = TransactionModel.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD)
            .description("Deposit")
            .dstAccount("12ERGD67890")
            .build();
    private TransactionDTO mockDepositEntity = TransactionDTO.builder()
            .amount(BigDecimal.TEN)
            .currency(CurrencyEnum.GLD.name())
            .type(TransactionTypeEnum.DEPOSIT.name())
            .description("Deposit")
            .accountNumber("12ERGD67890")
            .build();
    private Date currentDate = new Date();

    @BeforeAll
    public void setup() {
        mockSrcTransferEntity.setDate(currentDate);
        mockDstTransferEntity.setDate(currentDate);
        mockDepositEntity.setDate(currentDate);
    }

    @Test
    public void should_createTransactionsControllerService() {
        assertNotNull(service);
    }

    @Test
    public void should_createOneTransaction_andUpdateBalance() {
        when(accountsClient.getByAccountNumber("12ERGD12345")).thenReturn(mockSrcAccountDTO);
        when(accountsClient.getByAccountNumber("12ERGD67890")).thenReturn(null);
        try (MockedStatic<TransactionsConverter> converter = Mockito.mockStatic(TransactionsConverter.class)) {
            converter.when(() -> TransactionsConverter.toSrcTransferDTO(any(), any())).thenReturn(mockSrcTransferEntity);
            converter.when(() -> TransactionsConverter.toDstTransferDTO(any(), any())).thenReturn(mockDstTransferEntity);
        }

        service.transfer(mockTransferRequest);

        verify(transactionsClient, times(1)).create((TransactionDTO) any());
        verify(accountsClient, times(1)).updateAccountBalance(any(), any());
    }

    @Test
    public void should_createTwoTransactions_andUpdateBalance() {
        when(accountsClient.getByAccountNumber("12ERGD12345")).thenReturn(mockSrcAccountDTO);
        when(accountsClient.getByAccountNumber("12ERGD67890")).thenReturn(mockDstAccountDTO);
        try (MockedStatic<TransactionsConverter> converter = Mockito.mockStatic(TransactionsConverter.class)) {
            converter.when(() -> TransactionsConverter.toSrcTransferDTO(any(), any())).thenReturn(mockSrcTransferEntity);
            converter.when(() -> TransactionsConverter.toDstTransferDTO(any(), any())).thenReturn(mockDstTransferEntity);
        }

        service.transfer(mockTransferRequest);

        verify(transactionsClient, times(1)).createMultiple((List<TransactionDTO>) any());
        verify(accountsClient, times(2)).updateAccountBalance(any(), any());
    }

    @Test
    public void should_createDepositTransaction_andUpdateBalance() {
        when(accountsClient.getByAccountNumber("12ERGD12345")).thenReturn(mockSrcAccountDTO);
        when(accountsClient.getByAccountNumber("12ERGD67890")).thenReturn(mockDstAccountDTO);
        try (MockedStatic<TransactionsConverter> converter = Mockito.mockStatic(TransactionsConverter.class)) {
            converter.when(() -> TransactionsConverter.toDepositDTO(any(), any())).thenReturn(mockDepositEntity);
        }

        service.deposit(mockDepositRequest);

        verify(transactionsClient, times(1)).create((TransactionDTO) any());
        verify(accountsClient, times(1)).updateAccountBalance(any(), any());
    }
}