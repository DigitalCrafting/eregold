package org.digitalcrafting.arkenstone.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    @Autowired
    private ArkenstoneProperties arkenstoneProps;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routesBuilder = builder.routes();

        for (ArkenstoneProperties.ArkenstoneService service : arkenstoneProps.getServices()) {
            routesBuilder.route(
                    service.getId(),
                    r -> r.path(service.getPath())
                            .or()
                            .path(service.getPath() + "/**")
                            .uri(service.getUrl())
            );
        }

        return routesBuilder.build();
    }

}
