package org.spring.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {
    /*public Mono<String> fallback() {
        return Mono.just("fallback");
    }*/
	@RequestMapping("/fallback")
	public String fallback() {
		return "Spring Cloud GateWay FallBack!";
	}
}
