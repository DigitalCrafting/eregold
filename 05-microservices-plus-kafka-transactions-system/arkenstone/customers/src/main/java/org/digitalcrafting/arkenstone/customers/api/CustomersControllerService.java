package org.digitalcrafting.arkenstone.customers.api;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.customers.domain.CustomerDTO;
import org.digitalcrafting.arkenstone.customers.domain.CustomersConverter;
import org.digitalcrafting.arkenstone.customers.repository.CustomerEntity;
import org.digitalcrafting.arkenstone.customers.repository.CustomersEntityManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersControllerService {
    private final CustomersEntityManager entityManager;

    public CustomerDTO getCustomer(String customerId, String email) {
        CustomerEntity entity = null;

        if (customerId != null) {
            entity = entityManager.getByCustomerId(customerId);

        } else if (email != null) {
            entity = entityManager.getByEmail(email);
        }

        return CustomersConverter.toDTO(entity);
    }

    public void createCustomer(CustomerDTO customerDTO) {
        CustomerEntity entity = CustomersConverter.toEntity(customerDTO);
        entityManager.insert(entity);
    }
}
