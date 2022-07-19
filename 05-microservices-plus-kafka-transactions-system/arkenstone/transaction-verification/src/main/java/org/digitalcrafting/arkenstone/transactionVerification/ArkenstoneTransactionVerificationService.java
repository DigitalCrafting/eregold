package org.digitalcrafting.arkenstone.transactionVerification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy
public class ArkenstoneTransactionVerificationService {
    public static void main(String[] args) {
        SpringApplication.run(ArkenstoneTransactionVerificationService.class, args);
    }
}