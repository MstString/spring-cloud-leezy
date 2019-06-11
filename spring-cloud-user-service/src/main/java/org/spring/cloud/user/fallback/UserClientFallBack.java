package org.spring.cloud.user.fallback;

import org.spring.cloud.user.dataservice.IDataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("UserClientFallBack")
public class UserClientFallBack implements IDataService{
	
	public String getDefaultUser() {
		return new String("get DefaultUser failed");
	}
	
	public String getContextUserId() {
		return new String("get ContextUserId failed");
	}

	public String getFromFeign(String name) {
		return new String("get Input Name failed");
	}
}
