package org.digitalcrafting.arkenstone.api.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomersController {
    private final CustomersControllerService service;
}
