package org.example.jobs_agent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // Injecte la valeur de la propriété depuis application.properties
    @Value("${app.tools.freelancer.base-url}")
    private String freelancerToolBaseUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);  // 10 secondes
        requestFactory.setReadTimeout(120000);

        return builder
                .requestFactory(() -> requestFactory)
                .rootUri(freelancerToolBaseUrl) // Utilise la propriété injectée
                .build();
    }
}