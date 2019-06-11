package org.spring.cloud.zuul.server;

import org.spring.cloud.zuul.server.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulServerApplication 
{
    public static void main(String[] args)
    {
    	SpringApplication.run(ZuulServerApplication.class, args);
    }
    
    // 把自实现的Filter注入Spring Bean容器
    @Bean
    public AuthFilter preRequestFilter() {
    	System.out.println("触发了用户鉴权操作。");
    	return new AuthFilter();
    }
}
