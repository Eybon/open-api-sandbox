package fr.forge.openapi.java.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "externe.endpoint.formation")
public class FormationExterneConfiguration {
    String url;
}
