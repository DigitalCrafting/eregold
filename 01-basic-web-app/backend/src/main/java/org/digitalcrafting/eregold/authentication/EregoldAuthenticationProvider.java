package org.digitalcrafting.eregold.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class EregoldAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // TODO validate password against database
        log.info("===========TESTING============ Inside EregoldAuthenticationProvider");
        EregoldAuthentication eregoldAuthentication = new EregoldAuthentication();
        eregoldAuthentication.setGrantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        eregoldAuthentication.setAuthenticated(true);
        return eregoldAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EregoldAuthentication.class.equals(aClass);
    }
}
