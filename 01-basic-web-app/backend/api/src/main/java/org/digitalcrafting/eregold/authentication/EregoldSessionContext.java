package org.digitalcrafting.eregold.authentication;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EregoldSessionContext {
    private String userId;
    // TODO expiration
    private String token;
}