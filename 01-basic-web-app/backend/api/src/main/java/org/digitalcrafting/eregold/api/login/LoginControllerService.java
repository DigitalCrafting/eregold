package org.digitalcrafting.eregold.api.login;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.repository.users.UserEntity;
import org.digitalcrafting.eregold.repository.users.UsersMapper;
import org.digitalcrafting.eregold.utils.EregoldJWTUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

@Service
@RequiredArgsConstructor
public class LoginControllerService {
    private final UsersMapper usersMapper;
    private final EregoldJWTUtils jwtUtils;

    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        UserEntity userEntity = usersMapper.getByUserId(request.getUserId());
        if (userEntity == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] passwordb = Charset.forName("UTF-8").encode(CharBuffer.wrap(request.getPassword())).array();
        if (BCrypt.checkpw(passwordb, userEntity.getPasswordHash())) {
            String token = jwtUtils.generateAccessToken(userEntity.getUserId());
            return ResponseEntity.ok(new LoginResponse(token));
        }

        return ResponseEntity.badRequest().build();
    }
}