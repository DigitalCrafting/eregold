package org.digitalcrafting.arkenstone.customers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
}
