package org.spring.cloud.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "org")
public class UriConfigurationProperties {
	private String httpuri = "http://127.0.0.1:9091/getDefaultUser";

	public String getHttpuri() {
		return httpuri;
	}

	public void setHttpuri(String httpuri) {
		this.httpuri = httpuri;
	}

}
