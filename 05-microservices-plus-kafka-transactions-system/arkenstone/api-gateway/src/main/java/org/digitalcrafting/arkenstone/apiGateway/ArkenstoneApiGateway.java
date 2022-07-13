package org.digitalcrafting.arkenstone.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ArkenstoneProperties.class)
public class ArkenstoneApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ArkenstoneApiGateway.class, args);
    }
}
