package org.digitalcrafting.eregold.api.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationControllerTest {

    @Autowired
    private RegistrationController controller;

    @Test
    public void should_createRegistrationController() {
        assertNotNull(controller);
    }
}