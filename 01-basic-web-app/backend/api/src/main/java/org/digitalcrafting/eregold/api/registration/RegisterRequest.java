package org.digitalcrafting.eregold.api.registration;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private byte[] password;
    private String firstName;
    private String lastName;
}
