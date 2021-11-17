package org.digitalcrafting.eregold.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class EregoldAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest httpServletRequest) {
        // TODO extract username and password
        log.info("===========TESTING============ Inside EregoldAuthenticationConverter");
        return new EregoldAuthentication();
    }
}
