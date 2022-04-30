package org.digitalcrafting.eregold.repository.clients.customers;

import org.springframework.cloud.openfeign.FeignClient;

/* TODO Implement Feign client */
@FeignClient
public interface CustomersClient {
    CustomerDTO getByEmail(String email);

    CustomerDTO getByCustomerId(String customerId);

    void insert(CustomerDTO entity);
}
