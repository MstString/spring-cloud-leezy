package org.spring.cloud.zuul.server.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.common.exception.BaseException;
import org.spring.cloud.common.exception.BaseExceptionBody;
import org.spring.cloud.common.exception.CommonError;
import org.spring.cloud.common.vo.User;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

// 自定义一个鉴权过滤器
public class AuthFilter extends ZuulFilter {
	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	
	// 使用返回值设置该Filter是否执行
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext rc = RequestContext.getCurrentContext();
		authUser(rc);
		return null;
	}
	// 使用返回值设置Filter的类型、pre、route、post、error类型
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	private static Map<String, String> httpRequestToMap(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
	
	public static void authUser(RequestContext ctx) {
		// 从RequestContext上下文中获取HttpServletRequest
		HttpServletRequest request = ctx.getRequest();
		System.out.println("User Service From Port: " + request.getServerPort());
		Map<String, String> header = httpRequestToMap(request);
		// 从Header中获取userId
		String userId = header.get(User.CONTEXT_KEY_USERID);
		if(StringUtils.isEmpty(userId)) {
			try {
				BaseException BaseException = new BaseException(CommonError.USER_EMPTY_ERROR.getCode(), CommonError.USER_EMPTY_ERROR.getCodeEn(), CommonError.USER_EMPTY_ERROR.getMessage(),1L);
				BaseExceptionBody errorBody = new BaseExceptionBody(BaseException);
				// 对该请求禁止路由
				ctx.setSendZuulResponse(false);
				// 供post filter使用
				ctx.setResponseStatusCode(401);
				// 供post filter使用
				ctx.setResponseBody(JSONObject.toJSON(errorBody).toString());
			} catch (Exception e) {
				logger.error("println message error", e);
			}
		}else {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				ctx.addZuulRequestHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
}
