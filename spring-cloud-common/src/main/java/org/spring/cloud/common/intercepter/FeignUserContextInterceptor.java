package org.spring.cloud.common.intercepter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

// 拦截利用Feign进行服务间调用的请求，将用户属性放在Header里
public class FeignUserContextInterceptor implements RequestInterceptor {
	
	public void apply(RequestTemplate template) {
		System.out.println("执行了Feign调用请求。。。");
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
        	while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }
        }              
	}
}
