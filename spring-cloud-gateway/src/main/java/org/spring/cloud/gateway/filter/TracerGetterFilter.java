package org.spring.cloud.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import brave.Tracer;
import reactor.core.publisher.Mono;

@Component
public class TracerGetterFilter implements GlobalFilter, Ordered {
	
	@Autowired
	Tracer tracer;
	
	public int getOrder() {
		return 0;
	}

	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String tracerid = tracer.currentSpan().context().traceIdString();
		// 将TraceID的信息放入到响应头
		exchange.getResponse().getHeaders().add("TracerID", tracerid);
		System.out.println(exchange.getRequest().getHeaders().toString());
		System.out.println(tracerid);
		return chain.filter(exchange);
	}

}
