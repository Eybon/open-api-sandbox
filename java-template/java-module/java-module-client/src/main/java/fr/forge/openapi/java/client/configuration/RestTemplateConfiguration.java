package fr.forge.openapi.java.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        // Création client restTemplate minimal
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

}
