package org.digitalcrafting.eregold.api.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionsControllerTest {
    @Autowired
    private TransactionsController controller;

    @Test
    public void should_createTransferController() {
        assertNotNull(controller);
    }
}