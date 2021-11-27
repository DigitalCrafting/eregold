package org.digitalcrafting.eregold.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Component
public class EregoldAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest httpServletRequest) {
        log.info("===========TESTING============ Inside EregoldAuthenticationConverter");
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String encoded = authorizationHeader.substring("Basic ".length());
            byte[] tokenAsByteArr = Base64.getDecoder().decode(encoded.getBytes(StandardCharsets.UTF_8));

            EregoldAuthentication eregoldAuthentication = new EregoldAuthentication();
            eregoldAuthentication.setToken(new String(tokenAsByteArr));
            return eregoldAuthentication;
        } else {
            return null;
        }
    }
}
