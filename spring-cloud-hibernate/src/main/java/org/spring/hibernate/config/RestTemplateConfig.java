package org.spring.hibernate.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-cloud-leezy
 * @description: RestTemplate的跨类定制
 * @author: LEEZY
 * @create: 2019-03-22 15:52
 **/

@Configuration
public class RestTemplateConfig {
    @Bean(name="remoteRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthorization("ADMIN", "ADMIN").build().setInterceptors();
    }
}
