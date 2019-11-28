package org.spring.cloud.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.user.dataservice.IDataService;
import org.spring.cloud.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService{
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	@Qualifier("UserClientFallBack")
	private IDataService dataService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getDefaultUser() {
		return dataService.getDefaultUser();
	}
	
	public String getContextUserId() {
		return dataService.getContextUserId();
	}
	
	public List<String> getProviderData() {
		List<String> result = restTemplate.getForObject("http://data-service/getProviderData", List.class);
		return result;
	}

	public String getFromFeign(String name) {
		log.info("client sent. Feign 方式, 参数: {}", name);
    	String result = dataService.getFromFeign(name);
    	log.info("client received. Feign 方式, 结果: {}", name);
    	return result;
	}

	public String getFromRestTemplate(String name) {
		log.info("client sent. RestTemplate方式, 参数: {}",name);

        String url = "http://data-service/getFromRestTemplate?name="+name;
        String result = restTemplate.getForObject(url,String.class);

        log.info("client received. RestTemplate方式, 结果: {}",result);
        return result;
	}
}
