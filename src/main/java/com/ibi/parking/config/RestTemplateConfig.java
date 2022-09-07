package com.ibi.parking.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @ConfigurationProperties(prefix = "rest.template.conn")
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    public RestTemplate customRestTemplate() {
        return new RestTemplate(customHttpRequestFactory());
    }
}
