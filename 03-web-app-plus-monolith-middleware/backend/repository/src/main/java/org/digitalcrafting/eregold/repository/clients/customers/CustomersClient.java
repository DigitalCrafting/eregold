package org.digitalcrafting.eregold.repository.clients.customers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "CustomersClient", url = "${arkenstone.url}/v1/customers")
public interface CustomersClient {
    @GetMapping
    CustomerDTO getCustomer(@RequestParam(required = false) String customerId, @RequestParam(required = false) String email);

    @PostMapping
    void createCustomer(@RequestBody CustomerDTO entity);
}
