package org.spring.hibernate.controller;

import org.spring.hibernate.api.UserAPI;
import org.spring.hibernate.bo.UserBO;
import org.spring.hibernate.config.RestTemplateConfig;
import org.spring.hibernate.service.ITestService;
import org.spring.hibernate.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.naming.NameParser;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: spring-cloud-leezy
 * @description: RestTemplate测试
 * @author: LEEZY
 * @create: 2019-03-22 15:19
 **/
@RestController
public class RestTemplateController {

    // 按照类型进行匹配
    // @Autowired
    // @Qualifier("restTemplateConfig")
    // 按照名字进行匹配
    @Resource(name = "remoteRestTemplate")
    private RestTemplate restTemplateConfig;

    private RestTemplate restTemplate;

    /** 
    * @Description: 由于不同的rest服务调用可能需要不同的RestTemplate配置，根据适用范围通常有两种方式进行配置。RestTemplate的单类定制
    * @Param: [restTemplateBuilder] 
    * @return:  
    * @Author: LEEZY
    * @Date: 2019/3/28 
    */ 
    public RestTemplateController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
    * @Description: 通过RestTempalte.getForEntity方法来查询
    * @Param: [id] 
    * @return: org.spring.hibernate.bo.UserBO 
    * @Author: LEEZY
    * @Date: 2019/3/22 
    */ 
    @GetMapping(value = UserAPI.REMOTE_FIND_BY_ID)
    public UserBO GetRestTemplate(@RequestParam(value = "id") Integer id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        restTemplateConfig.getInterceptors().add(
                new BasicAuthorizationInterceptor("ADMIN", "ADMIN"));
        // ResponseEntity<UserBO> responseEntity = restTemplateConfig.restTemplate().getForEntity("http://localhost:8000/user/find_by_id?id={id}", UserBO.class, map);
        ResponseEntity<UserBO> responseEntity = restTemplateConfig.getForEntity("http://localhost:8000/user/find_by_id?id={id}", UserBO.class, map);
        return responseEntity.getBody();
    }

    /**
    * @Description: 通过RestTemplate.exchange方法来查询
    * @Param: [id]
    * @return: org.spring.hibernate.bo.UserBO
    * @Author: LEEZY
    * @Date: 2019/3/27
    */
    @GetMapping(value = UserAPI.REMOTE_FIND_BY_ID + "_exchange")
    public UserBO getExchange(@RequestParam(value = "id") Integer id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        ResponseEntity<UserBO> responseEntity = restTemplateConfig.exchange("http://localhost:8000/user/find_by_id?id={id}", HttpMethod.GET, null, UserBO.class, map);
        return responseEntity.getBody();
    }

    /**
    * @Description: 通过RestTemplate.postForObject方法来添加
    * @Param: [userVO]
    * @return: org.spring.hibernate.bo.UserBO
    * @Author: LEEZY
    * @Date: 2019/3/27
    */
    @PostMapping(value = UserAPI.REMOTE_SAVE)
    public UserBO SaveRestTemplateObject(@RequestBody UserVO userVO) {
        System.out.println("Save Method!");
        UserBO userBO = new UserBO();
        userBO.setId(userVO.getId());
        userBO.setUsername(userVO.getUsername());
        userBO.setPassword(userVO.getPassword());
        HttpEntity<UserBO> request = new HttpEntity<>(userBO);
        return restTemplateConfig.postForObject("http://localhost:8000/user/save", request, UserBO.class);
    }

    /**
    * @Description: ERROR
    * @Param: [userVO]
    * @return: org.spring.hibernate.bo.UserBO
    * @Author: LEEZY
    * @Date: 2019/3/27
    */
    @PostMapping(value = "/save_by_rest_entity")
    public UserBO SaveRestTemplateEntity(@RequestBody UserVO userVO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("id", userVO.getId());
        body.add("username", userVO.getUsername());
        body.add("password", userVO.getPassword());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<UserBO> responseEntity = restTemplateConfig.postForEntity("http://localhost:8000/user/save", request, UserBO.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @PostMapping(value = "/save_Exchange")
    public UserBO SaveRestTemplateExchange(@RequestParam(value = "id") Integer id, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("id", id);
        body.add("username", username);
        body.add("password", password);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<UserBO> responseEntity = restTemplateConfig.exchange("http://localhost:8000/user/save", HttpMethod.POST, request, UserBO.class);
        return responseEntity.getBody();
    }
}
