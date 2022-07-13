package org.digitalcrafting.arkenstone.apiGateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "arkenstone")
public class ArkenstoneProperties {

    private List<ArkenstoneService> services;

    @Data
    public static class ArkenstoneService {
        private String url;
        private String path;
        private String id;
    }
}
