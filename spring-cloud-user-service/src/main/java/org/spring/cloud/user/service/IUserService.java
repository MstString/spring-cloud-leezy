package org.spring.cloud.user.service;

import java.util.List;

public interface IUserService {
	public String getDefaultUser();
	
    public String getContextUserId();
    
    public List<String> getProviderData();
    
    public String getFromFeign(String name);
    
    public String getFromRestTemplate(String name);
}
