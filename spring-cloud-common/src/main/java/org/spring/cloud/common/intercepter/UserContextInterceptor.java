package org.spring.cloud.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.common.context.UserContextHolder;
import org.spring.cloud.common.util.HttpConvertUtil;
import org.spring.cloud.common.vo.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserContextInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);
	
	/*@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2) throws Exception {
		User user = new User(HttpConvertUtil.httpRequestToMap(request));
		if(StringUtils.isEmpty(user.getUserId()) && StringUtils.isEmpty(user.getUserName())) {
			log.error("Something is error, the user is null...");
			return false;
		}
		UserContextHolder.set(user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse respone, Object arg2, Exception arg3)
			throws Exception {
		UserContextHolder.shutdown();
	}*/
}
