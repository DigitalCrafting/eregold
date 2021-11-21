package org.digitalcrafting.eregold.authentication;

import lombok.extern.slf4j.Slf4j;
import org.digitalcrafting.eregold.utils.EregoldHashUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Component
public class EregoldAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest httpServletRequest) {
        log.info("===========TESTING============ Inside EregoldAuthenticationConverter");
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader.startsWith("Basic ")) {
            String encoded = authorizationHeader.substring("Basic ".length());
            byte[] userIdAndPass = Base64.getDecoder().decode(encoded.getBytes(StandardCharsets.UTF_8));

            int colonIndex = Arrays.binarySearch(userIdAndPass, (byte) ':');
            byte[] userIdByteArray = Arrays.copyOfRange(userIdAndPass, 0, colonIndex);
            String userId = new String(userIdByteArray);

            byte[] passByteArray = Arrays.copyOfRange(userIdByteArray, colonIndex, userIdByteArray.length);
            Optional<String> passHash = EregoldHashUtils.createPassHash(passByteArray, userId);

            EregoldAuthentication eregoldAuthentication = new EregoldAuthentication();
            eregoldAuthentication.setPasswordHash(passHash.get());
            eregoldAuthentication.setPrincipal(new EregoldPrincipal(userId));
            return eregoldAuthentication;
        } else {
            return null;
        }
    }
}
