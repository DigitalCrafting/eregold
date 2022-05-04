package org.digitalcrafting.eregold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableRedisRepositories
@EnableFeignClients
public class EregoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(EregoldApplication.class, args);
    }
}
