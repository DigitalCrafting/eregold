package org.digitalcrafting.eregold.api.registration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.digitalcrafting.eregold.dataAccess.repository.customers.CustomerEntity;
import org.digitalcrafting.eregold.dataAccess.repository.customers.CustomersMapper;
import org.digitalcrafting.eregold.dataAccess.repository.users.UserEntity;
import org.digitalcrafting.eregold.dataAccess.repository.users.UsersMapper;
import org.digitalcrafting.eregold.utils.EregoldHashUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationControllerService {
    private final UsersMapper usersMapper;
    private final CustomersMapper customersMapper;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (usersMapper.getByUserId(request.getEmail()) != null) {
            return new RegisterResponse(RegistrationStatusEnum.ALREADY_EXISTS);
        }

        Optional<String> customerId = createCustomer(request);
        if (customerId.isPresent()) {
            return new RegisterResponse(customerId.get());
        } else {
            return new RegisterResponse(RegistrationStatusEnum.CREATION_FAILED);
        }

    }

    @Transactional
    public Optional<String> createCustomer(RegisterRequest request) {
        Optional<String> passHash = EregoldHashUtils.createPassHash(request.getPassword(), request.getEmail());
        if (passHash.isEmpty()) {
            return Optional.empty();
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(request.getEmail())
                .passwordHash(passHash.get())
                .build();
        usersMapper.insert(userEntity);

        String customerId = generateCustomerId();

        CustomerEntity customerEntity = CustomerEntity.builder()
                .customerId(customerId)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customersMapper.insert(customerEntity);
        return Optional.of(customerId);
    }

    private String generateCustomerId() {
        long dateSeconds = Instant.now().toEpochMilli();
        String customerId = String.valueOf(dateSeconds).substring(0, 8);
        while (customersMapper.getByCustomerId(customerId) != null) {
            dateSeconds = Instant.now().toEpochMilli();
            customerId = String.valueOf(dateSeconds).substring(0, 8);
        }
        return customerId;
    }
}
