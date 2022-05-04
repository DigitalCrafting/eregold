package org.digitalcrafting.eregold.api.registration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.digitalcrafting.eregold.repository.clients.customers.CustomerDTO;
import org.digitalcrafting.eregold.repository.clients.customers.CustomersClient;
import org.digitalcrafting.eregold.repository.db.users.UserEntity;
import org.digitalcrafting.eregold.repository.db.users.UsersEntityManager;
import org.digitalcrafting.eregold.utils.EregoldPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationControllerService {
    private final UsersEntityManager usersEntityManager;
    private final CustomersClient customersClient;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (usersEntityManager.getByUserId(request.getEmail()) != null) {
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
        Optional<String> encodedPassword = EregoldPasswordEncoder.encodePassword(request.getPassword(), request.getEmail());
        if (encodedPassword.isEmpty()) {
            return Optional.empty();
        }

        UserEntity userEntity = UserEntity.builder()
                .userId(request.getEmail())
                .passwordHash(encodedPassword.get())
                .build();
        usersEntityManager.insert(userEntity);

        String customerId = generateCustomerId();

        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerId(customerId)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customersClient.createCustomer(customerDTO);
        return Optional.of(customerId);
    }

    private String generateCustomerId() {
        long dateSeconds = Instant.now().toEpochMilli();
        String customerId = String.valueOf(dateSeconds).substring(0, 8);
        while (customersClient.getCustomer(customerId, null) != null) {
            dateSeconds = Instant.now().toEpochMilli();
            customerId = String.valueOf(dateSeconds).substring(0, 8);
        }
        return customerId;
    }
}
