package org.digitalcrafting.eregold.authentication;

import lombok.extern.slf4j.Slf4j;
import org.digitalcrafting.eregold.repository.users.UserEntity;
import org.digitalcrafting.eregold.repository.users.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class EregoldAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("===========TESTING============ Inside EregoldAuthenticationProvider");
        if (authentication instanceof EregoldAuthentication) {
            EregoldAuthentication eregoldAuthentication = (EregoldAuthentication) authentication;
            UserEntity entity = usersMapper.getByUserId(authentication.getName());
            if (entity != null && entity.getPasswordHash().equals(eregoldAuthentication.getPasswordHash())) {
                eregoldAuthentication.setGrantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
                eregoldAuthentication.setAuthenticated(true);
                return eregoldAuthentication;
            }
        }
        throw new BadCredentialsException("User not authenticated");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EregoldAuthentication.class.equals(aClass);
    }
}
