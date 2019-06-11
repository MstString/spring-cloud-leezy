package org.spring.cloud.user.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.user.dataservice.IDataService;
import org.spring.cloud.user.service.IUserService;
import org.spring.cloud.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
    private ExecutorService executorService;
	
	// 获取配置文件中默认用户
    @GetMapping("/getDefaultUser")
    public String getDefaultUser() {
        return userService.getDefaultUser();
    }
    
    // 获取系统上下文用户
    @GetMapping("/getContextUserId")
    public String getContextUserId(){
        return userService.getContextUserId();
    }
    
    //获取供应商数据
    @GetMapping("/getProviderData")
    public List<String> getProviderData(){
        return userService.getProviderData();
    }
    
    @GetMapping("/getFromFeign")
    public String getFromFeign(@RequestParam("name")String name) {
    	return userService.getFromFeign(name);
    }
    
    @GetMapping("/getFromRestTemplate")
    public String getFromRestTemplate(String name) {
    	return userService.getFromRestTemplate(name);
    }
    
    @GetMapping("/getFromThread")
    public String hello(String name) throws ExecutionException, InterruptedException {
        log.info("client sent. 子线程方式, 参数: {}",name);

        Future future = executorService.submit(() -> {
            log.info("client sent. 进入子线程, 参数: {}",name);
            String result = userService.getDefaultUser();
            return result;
        });
        String result = (String) future.get();
        log.info("client received. 返回主线程, 结果: {}",result);
        return result;
    }
    
}
