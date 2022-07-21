package org.digitalcrafting.arkenstone.transactionVerification.domain;

import org.digitalcrafting.arkenstone.transactionVerification.repository.accounts.AccountsMapper;
import org.digitalcrafting.arkenstone.transactionVerification.repository.transactions.TransactionsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @Test
    public void should_acceptOwnTransfer() {
        // TODO
    }

    @Test
    public void should_acceptForeignTransfer() {
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalance_isZero() {
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalance_isNot_currentBalance() {
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalanceAfterTransaction_isLessThan_Zero() {
        // TODO
    }

    @Test
    public void should_rejectTransfer_when_historicallyCalculatedBalanceAfterTransaction_isNot_AvailableBalance() {
        // TODO
    }
}