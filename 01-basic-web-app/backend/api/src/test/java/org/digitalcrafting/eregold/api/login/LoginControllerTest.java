package org.digitalcrafting.eregold.api.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {
    @Autowired
    private LoginController controller;

    @Test
    public void should_createLoginController() {
        assertNotNull(controller);
    }
}