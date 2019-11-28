package org.spring.cloud.gateway.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.Tracer;

@Configuration
public class GateWayConfig {

	@Autowired
	Tracer tracer;
	
	public void getTraceID() {
		System.out.println(tracer.currentSpan().context().traceIdString());
	}
	
	@Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfigurationProperties uriConfiguration) {
		String httpUri = uriConfiguration.getHttpuri();

		return builder.routes()
			.route("header_route", p -> p
					// header路由断言工厂，如何符合则进行调用
					.header("x-customs-leezy", "leezy")
	                .filters(f -> f.hystrix(config -> config.setName("mycmd").setFallbackUri("forward:/fallback")))
	                .uri(httpUri))
    		.route("add_Response_header_route", r -> r.path("/getTracer").filters(f -> f.addResponseHeader("scd-trace-id",
    		"can't get tracerID")).uri(httpUri))
    		.build();
    }
}
