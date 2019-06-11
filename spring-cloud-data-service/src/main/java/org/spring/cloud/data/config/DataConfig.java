package org.spring.cloud.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

// @Component
// @RefreshScope
//-@ConfigurationProperties(prefix = "top.leezy.www")
public class DataConfig {
	
	@Value("${top.leezy.www.defaultUser}")
	private String defaultUser;
	
	public String getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}
}
