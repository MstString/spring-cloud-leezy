package org.spring.cloud.data.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.common.context.UserContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

@RestController
public class DataController {

	private static final Logger log = LoggerFactory.getLogger(DataController.class);

	/*
	 * @Autowired 
	 * private DataConfig dataConfig;
	 */
	ConfigChangeListener changeListener = new ConfigChangeListener() {
		@Override
		public void onChange(ConfigChangeEvent changeEvent) {
			log.info("Changes for namespace {}", changeEvent.getNamespace());
			for (String key : changeEvent.changedKeys()) {
				ConfigChange change = changeEvent.getChange(key);
				log.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}", change.getPropertyName(),
						change.getOldValue(), change.getNewValue(), change.getChangeType());
			}
		}
	};
	
	@GetMapping("/getDefaultUser")
	public String getDefaultUser(HttpServletRequest request) {
		// return dataConfig.getDefaultUser();
		// Config appConfig = ConfigService.getConfig("SPRINGCLOUD");
		Config appConfig = ConfigService.getAppConfig();
		appConfig.addChangeListener(changeListener);
		return appConfig.getProperty("LEEZY", "SAKURA");
	}

	@GetMapping("/getContextUserId")
	public String getContextUserId() {
		return UserContextHolder.currentUser().getUserId();
	}

	@GetMapping("/getProviderData")
	public List<String> getProviderData() {
		List<String> provider = new ArrayList<String>();
		provider.add("ZDH");
		provider.add("ABCDC");
		return provider;
	}

	@GetMapping("/getFromFeign")
	public String getFromFeign(String name) {
		log.info("server received. 参数: {}", name);
		String result = "hello, " + name;
		log.info("server sent. 结果: {}", result);
		return result;
	}

	@GetMapping("/getFromRestTemplate")
	public String getFromRestTemplate(@RequestParam("name") String name) {
		log.info("server received. 参数: {}", name);
		String result = "hello, " + name;
		log.info("server sent. 结果: {}", result);
		return result;
	}
}