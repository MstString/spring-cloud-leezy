package org.spring.hibernate.controller;

import com.netflix.client.http.HttpResponse;
import org.spring.cloud.common.util.CopyUtil;
import org.spring.hibernate.api.UserAPI;
import org.spring.hibernate.bo.UserBO;
import org.spring.hibernate.po.UserPO;
import org.spring.hibernate.service.impl.UserServiceImpl;
import org.spring.hibernate.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: spring-cloud-leezy
 * @description: 业务逻辑控制层
 * @author: LEEZY
 * @create: 2019-03-20 10:35
 **/
@RestController
@Validated
public class UserController {

    @Autowired
    UserServiceImpl userService;
    
    /** 
    * @Description: 开放查询RestFul API
    * @Param: [id] 
    * @return: org.spring.hibernate.vo.UserVO 
    * @Author: LEEZY
    * @Date: 2019/3/22 
    */ 
    @GetMapping(value = UserAPI.FIND_BY_ID)
    public UserVO getById(@RequestParam(value = "id", required = false) @NotNull(message = "{id.notNULL}") Integer id) {
        return CopyUtil.copyBean(userService.findById(id), UserVO.class);
    }

    /**
    * @Description: 测试{@link org.springframework.web.bind.annotation.PathVariable} {@link PathVariable}注解
    * @Param: [id]
    * @return: org.spring.hibernate.vo.UserVO
    * @Author: LEEZY
    * @Date: 2019/3/22
    */
    @GetMapping(value = UserAPI.FIND_BY_ID + "/{id}")
    public UserVO getByIdPath(@PathVariable Integer id) {
        return CopyUtil.copyBean(userService.findById(id), UserVO.class);
    }
    
    @PostMapping(value = UserAPI.SAVE, produces = "application/json")
    public UserVO save(@RequestBody HttpEntity<UserBO> userBO) {
        System.out.println(userBO);
        /* UserPO userPO = new UserPO();
        userPO.setId(userBO.getId());
        userPO.setUsername(userBO.getUsername());
        userPO.setPassword(userBO.getPassword());
        return CopyUtil.copyBean(userService.save(userPO), UserVO.class);*/

       return null;
    }
    

    /** 
    * @Description: 
     * @param id  
    * @return: java.lang.String 
    * @Author: LEEZY
    * @Date: 2019/4/2 
    */ 
    @DeleteMapping(value = UserAPI.DELETE)
    public String deleteById(@RequestParam(value = "id") Integer id) {
        userService.deleteById(id);
        return "Delete Success!";
    }

    @GetMapping(value = UserAPI.LISTALL)
    public List<UserBO> findAll() {
        List<UserBO> userBOList = userService.findAll();
        return userBOList;
    }

}
