package org.digitalcrafting.eregold.repository.clients.customers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
}
