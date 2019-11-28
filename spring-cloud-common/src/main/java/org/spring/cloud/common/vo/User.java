package org.spring.cloud.common.vo;

import java.util.HashMap;
import java.util.Map;

public class User {
    private static final long serialVersionUID = 1L;
    
    public final static String CONTEXT_KEY_USERID = "x-customs-leezy";
    
    public String userId;
    
    public String userName;
    
    public User() {
    	
    }
    
    public User(Map<String, String> headers) {
    	userId = headers.get(CONTEXT_KEY_USERID);
    }
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// 将User对象放在http header中
	public Map<String, String> toHttpHeaders() {
		Map<String, String> headers = new HashMap<>();
		headers.put(CONTEXT_KEY_USERID, userId);
		return headers;
	}
    
}
