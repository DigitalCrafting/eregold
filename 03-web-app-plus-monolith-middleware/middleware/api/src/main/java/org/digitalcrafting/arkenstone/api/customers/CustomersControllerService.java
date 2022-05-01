package org.digitalcrafting.arkenstone.api.customers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.arkenstone.domain.customers.CustomerDTO;
import org.digitalcrafting.arkenstone.domain.customers.CustomersConverter;
import org.digitalcrafting.arkenstone.repository.customers.CustomerEntity;
import org.digitalcrafting.arkenstone.repository.customers.CustomersEntityManager;
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
