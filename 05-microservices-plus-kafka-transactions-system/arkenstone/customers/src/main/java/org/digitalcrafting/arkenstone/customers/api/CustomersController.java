package org.digitalcrafting.arkenstone.customers.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.customers.domain.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomersController {
    private final CustomersControllerService service;

    @GetMapping
    public CustomerDTO getCustomer(@RequestParam(required = false) String customerId, @RequestParam(required = false) String email) {
        return service.getCustomer(customerId, email);
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customerDTO) {
        service.createCustomer(customerDTO);
    }

}
