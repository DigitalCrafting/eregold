package org.digitalcrafting.arkenstone.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ArkenstoneCustomersService {
    public static void main(String[] args) {
        SpringApplication.run(ArkenstoneCustomersService.class, args);
    }
}
