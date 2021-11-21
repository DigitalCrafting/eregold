package org.digitalcrafting.eregold.authentication;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EregoldSessionContext {
    private String userId;

    public boolean isLoggedIn() {
        EregoldAuthentication authentication = (EregoldAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }
}
