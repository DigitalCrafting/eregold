package org.digitalcrafting.eregold.dataAccess.repository.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    private String userId;
    private String passwordHash;
}
