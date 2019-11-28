package org.spring.hibernate.controller;

import org.spring.hibernate.service.ITestService;
import org.spring.hibernate.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: spring-cloud-leezy
 * @description: Controller测试类
 * @author: LEEZY
 * @create: 2019-03-28 18:08
 **/
public class TestController implements ITestService {

    @Override
    public String get(Integer id) {
        return "get";
    }

    @Override
    public String getPath(Integer id) {
        return "getPath";
    }

    @Override
    public String postBody(String name) {
        return "postBody";
    }
}
