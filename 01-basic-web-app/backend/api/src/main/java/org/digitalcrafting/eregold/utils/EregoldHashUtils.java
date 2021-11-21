package org.digitalcrafting.eregold.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Optional;

@Slf4j
public final class EregoldHashUtils {
    private static final String HASH_ALG = "SHA-256";

    private EregoldHashUtils() {
    }

    public static Optional<String> createPassHash(byte[] pass, String userId) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALG);
            String passHash = new String(messageDigest.digest(pass));
            return Optional.of(passHash);
        } catch (Exception exception) {
            log.info("Failed to hash password for user with email {}", userId, exception);
            return Optional.empty();
        }
    }
}
