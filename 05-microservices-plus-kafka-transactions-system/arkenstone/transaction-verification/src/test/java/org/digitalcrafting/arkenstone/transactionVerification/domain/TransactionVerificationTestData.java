package org.digitalcrafting.arkenstone.transactionVerification.domain;

import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountEntity;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionEntity;

import java.math.BigDecimal;
import java.util.List;

public class TransactionVerificationTestData {
    public AccountEntity MOCK_SOURCE_ACCOUNT = AccountEntity.builder()
            .accountName("Src account")
            .accountNumber("12ERGD12345")
            .currentBalance(new BigDecimal("10000"))
            .availableBalance(new BigDecimal("9000"))
            .currency("GLD")
            .build();

    public AccountEntity MOCK_DESTINATION_ACCOUNT = AccountEntity.builder()
            .accountName("Dst account")
            .accountNumber("12ERGD67890")
            .currentBalance(new BigDecimal("5000"))
            .availableBalance(new BigDecimal("5000"))
            .currency("GLD")
            .build();


    public TransactionEntity MOCK_HISTORICAL_TRANSACTION_1 = TransactionEntity.builder()
            .id(1L)
            .amount(new BigDecimal(8000))
            .currency("GLD")
            .description("Test transaction 1")
            .accountNumber("12ERGD12345")
            .type("DEPOSIT")
            .status("ACCEPTED")
            .build();
    public TransactionEntity MOCK_HISTORICAL_TRANSACTION_2 = TransactionEntity.builder()
            .id(2L)
            .amount(new BigDecimal(1000).negate())
            .currency("GLD")
            .description("Test transaction 2")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("12ERGD67890")
            .type("TRANSFER")
            .status("ACCEPTED")
            .build();
    public TransactionEntity MOCK_HISTORICAL_TRANSACTION_3 = TransactionEntity.builder()
            .id(3L)
            .amount(new BigDecimal(3000))
            .currency("GLD")
            .description("Test transaction 3")
            .accountNumber("12ERGD12345")
            .type("DEPOSIT")
            .status("ACCEPTED")
            .build();
    public TransactionEntity MOCK_HISTORICAL_TRANSACTION_4 = TransactionEntity.builder()
            .id(4L)
            .amount(new BigDecimal(10000).negate())
            .currency("GLD")
            .description("Test transaction 4")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("11SRBS111111")
            .type("TRANSFER")
            .status("ACCEPTED")
            .build();
    public TransactionEntity MOCK_HISTORICAL_TRANSACTION_5 = TransactionEntity.builder()
            .id(5L)
            .amount(new BigDecimal(2000))
            .currency("GLD")
            .description("Test transaction 5")
            .accountNumber("12ERGD12345")
            .type("DEPOSIT")
            .status("ACCEPTED")
            .build();



    public TransactionEntity MOCK_CORRECT_OWN_TRANSACTION = TransactionEntity.builder()
            .id(99L)
            .amount(new BigDecimal(1000).negate())
            .currency("GLD")
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("12ERGD67890")
            .type("TRANSFER")
            .status("PENDING")
            .build();
    public TransactionEntity MOCK_CORRECT_FOREIGN_TRANSACTION = TransactionEntity.builder()
            .id(99L)
            .amount(new BigDecimal(1000).negate())
            .currency("GLD")
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("11SRBS111111")
            .type("TRANSFER")
            .status("PENDING")
            .build();
    public TransactionEntity MOCK_TRANSACTION_WRONG_STATUS = TransactionEntity.builder()
            .id(99L)
            .amount(new BigDecimal(1000).negate())
            .currency("GLD")
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("12ERGD67890")
            .type("TRANSFER")
            .status("ACCEPTED")
            .build();
    public TransactionEntity MOCK_TRANSACTION_2000 = TransactionEntity.builder()
            .id(99L)
            .amount(new BigDecimal(2000).negate())
            .currency("GLD")
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("12ERGD67890")
            .type("TRANSFER")
            .status("PENDING")
            .build();
    public TransactionEntity MOCK_TRANSACTION_11000 = TransactionEntity.builder()
            .id(99L)
            .amount(new BigDecimal(11000).negate())
            .currency("GLD")
            .description("Test transfer")
            .accountNumber("12ERGD12345")
            .foreignAccountNumber("12ERGD67890")
            .type("TRANSFER")
            .status("PENDING")
            .build();

    public List<TransactionEntity> MOCK_HISTORY_CORRECT_BALANCE = List.of(
            MOCK_HISTORICAL_TRANSACTION_1,
            MOCK_HISTORICAL_TRANSACTION_2,
            MOCK_HISTORICAL_TRANSACTION_3
    );
    public List<TransactionEntity> MOCK_HISTORY_BALANCE_ZERO = List.of(
            MOCK_HISTORICAL_TRANSACTION_1,
            MOCK_HISTORICAL_TRANSACTION_2,
            MOCK_HISTORICAL_TRANSACTION_3,
            MOCK_HISTORICAL_TRANSACTION_4
    );
    public List<TransactionEntity> MOCK_HISTORY_BALANCE_NOT_CURRENT_BALANCE = List.of(
            MOCK_HISTORICAL_TRANSACTION_1,
            MOCK_HISTORICAL_TRANSACTION_2,
            MOCK_HISTORICAL_TRANSACTION_3,
            MOCK_HISTORICAL_TRANSACTION_5
    );
}
