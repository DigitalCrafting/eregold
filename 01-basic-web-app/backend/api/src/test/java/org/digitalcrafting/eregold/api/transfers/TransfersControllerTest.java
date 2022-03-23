package org.digitalcrafting.eregold.api.transfers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransfersControllerTest {
    @Autowired
    private TransfersController controller;

    @Test
    public void should_createTransferController() {
        assertNotNull(controller);
    }
}