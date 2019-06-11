package org.spring.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-cloud-leezy
 * @description: Default测试类
 * @author: LEEZY
 * @create: 2019-03-29 11:21
 **/
/*
 * RestTemplate 访问 Basic Authorization的API的示例
 */
@RestController
@RequestMapping("/guest")
class DefaultController {

    @GetMapping("/mybooks")
    @ResponseBody
    public String getMyBook() {
        return "My Book";
    }

    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    void setRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.basicAuthorization("ADMIN", "ADMIN")
                .build();
    }

    @GetMapping("/apibooks")
    @ResponseBody
    public String getApiBooks() {
        return restTemplate.getForObject("http://localhost:8080/api/books", String.class);

    }
}