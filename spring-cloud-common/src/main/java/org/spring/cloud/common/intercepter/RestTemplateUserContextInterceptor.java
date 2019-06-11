package org.spring.cloud.common.intercepter;

import java.io.IOException;
import java.util.Map;

import org.spring.cloud.common.context.UserContextHolder;
import org.spring.cloud.common.vo.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

// 使用RestTemplate进行服务间调用时拦截请求，User属性放入Header内
public class RestTemplateUserContextInterceptor implements ClientHttpRequestInterceptor {
	
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		System.out.println("执行了RestTemplate调用请求。。。");
		User user = UserContextHolder.currentUser();
		Map<String, String> headers = user.toHttpHeaders();
		for (Map.Entry<String, String> header : headers.entrySet()) {
			request.getHeaders().add(header.getKey(), header.getValue());
		}
		return execution.execute(request, body);
	}
}
