package org.digitalcrafting.eregold.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.security.Principal;

@Data
@AllArgsConstructor
public class EregoldPrincipal implements Principal, Serializable {
    private String userId;

    @Override
    public String getName() {
        return userId;
    }
}
