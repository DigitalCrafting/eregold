package org.digitalcrafting.eregold.api.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountsControllerTest {
    @Autowired
    private AccountsController controller;

    @Test
    public void should_createAccountsController() {
        assertNotNull(controller);
    }
}