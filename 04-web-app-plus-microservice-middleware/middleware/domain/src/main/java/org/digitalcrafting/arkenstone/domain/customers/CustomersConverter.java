package org.digitalcrafting.arkenstone.domain.customers;

import org.digitalcrafting.arkenstone.repository.customers.CustomerEntity;

public final class CustomersConverter {
    private CustomersConverter() {
    }

    public static CustomerEntity toEntity(CustomerDTO dto) {
        if (dto != null) {
            return CustomerEntity.builder()
                    .customerId(dto.getCustomerId())
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .email(dto.getEmail())
                    .build();
        }
        return null;
    }

    public static CustomerDTO toDTO(CustomerEntity entity) {
        if (entity != null) {
            return CustomerDTO.builder()
                    .customerId(entity.getCustomerId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .email(entity.getEmail())
                    .build();
        }
        return null;
    }


}
