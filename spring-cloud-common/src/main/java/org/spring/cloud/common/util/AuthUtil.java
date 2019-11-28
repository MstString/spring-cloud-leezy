package org.spring.cloud.common.util;

import javax.servlet.http.HttpServletResponse;

import org.spring.cloud.common.vo.User;
import org.springframework.util.StringUtils;

public class AuthUtil {
	public static boolean authUser(User user, HttpServletResponse response) {
		if(StringUtils.isEmpty(user.getUserId())) {
			return false;
		}else {
			return true;
		}
	}
}
