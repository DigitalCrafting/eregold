package org.digitalcrafting.eregold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableRedisRepositories
public class EregoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(EregoldApplication.class, args);
    }
}
