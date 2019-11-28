package org.spring.hibernate.service;

import org.springframework.web.bind.annotation.*;

/**
 * @program: spring-cloud-leezy
 * @description: 测试Bug接口
 * @author: LEEZY
 * @create: 2019-03-28 17:26
 **/
@RequestMapping(value = "/openapi")
public interface ITestService {

    String GET = "/get";
    String GET_PATH = "/get_path/{id}";
    String POST_BODY = "/get_body";

    @RequestMapping(value = GET, method = RequestMethod.GET)
    public String get(@RequestParam(name = "id") Integer id);

    @RequestMapping(value = GET_PATH, method = RequestMethod.GET)
    public String getPath(@PathVariable(name = "id") Integer id);

    @RequestMapping(value = POST_BODY, method = RequestMethod.POST)
    public String postBody(@RequestBody String name);

}
