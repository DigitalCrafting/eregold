package org.digitalcrafting.eregold.api.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/registration")
public class RegistrationController {
    private final RegistrationControllerService service;


}
