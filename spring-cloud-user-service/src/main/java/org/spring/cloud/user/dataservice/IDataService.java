package org.spring.cloud.user.dataservice;

import org.spring.cloud.user.fallback.UserClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// 定义了FeignClient的名称（微服务的名称），以及调用远程接口失败或者超时时的容错逻辑
// FallBack的类必须实现FeignClient标记的接口
@FeignClient(name = "data-service", fallback = UserClientFallBack.class)
public interface IDataService {
	
	@RequestMapping(value = "/getDefaultUser", method = RequestMethod.GET)
    public String getDefaultUser();
    
    @RequestMapping(value = "/getContextUserId", method = RequestMethod.GET)
    public String getContextUserId();
    
    @GetMapping("/getFromFeign")
    public String getFromFeign(@RequestParam("name")String name);
    
}
