package org.spring.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud-leezy
 * @description: Api测试类
 * @author: LEEZY
 * @create: 2019-03-29 11:21
 **/
/*
 * 需要进行 Basic Auth 的API
 */
@RestController
@RequestMapping("/api")
class ApiController {

    @GetMapping("/books")
    public String getBooks() {
        return "API book";
    }
}