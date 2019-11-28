package org.spring.cloud.user.controller;

import org.spring.cloud.user.properties.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestConfigController {

	@Autowired
	private ConfigInfoProperties configInfo;
	
	@RequestMapping("/getConfigInfo")
	public String getConfigInfo() {
		return configInfo.getConfig();
	}
	
}
