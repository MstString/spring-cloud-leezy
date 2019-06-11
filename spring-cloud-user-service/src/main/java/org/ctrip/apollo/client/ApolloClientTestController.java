package org.ctrip.apollo.client;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

@RestController
public class ApolloClientTestController {
	Config appConfig = ConfigService.getConfig("SPRINGCLOUD");
	
	/*appConfig.addChangeListener(new ConfigChangeListener() {
	    public void onChange(ConfigChangeEvent changeEvent) {
	        System.out.println("Changes for namespace " + changeEvent.getNamespace());
	        for (String key : changeEvent.changedKeys()) {
	            ConfigChange change = changeEvent.getChange(key);
	            System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
	        }
	    }
	});*/
	
	@RequestMapping("/getApolloConfig")
	public String getApolloConfig() {
		return appConfig.getProperty("LEEZY", null);
	}
}
