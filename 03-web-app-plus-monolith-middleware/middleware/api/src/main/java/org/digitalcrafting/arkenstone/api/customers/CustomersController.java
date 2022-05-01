package org.digitalcrafting.arkenstone.api.customers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.domain.customers.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomersController {
    private final CustomersControllerService service;

    @GetMapping
    public CustomerDTO getById(@RequestParam String customerId) {
        return service.getById(customerId);
    }

    @GetMapping
    public CustomerDTO getByEmail(@RequestParam String email) {
        return service.getByEmail(email);
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customerDTO) {
        service.createCustomer(customerDTO);
    }

}
