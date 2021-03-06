package org.spring.cloud.zuul.server.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class ZuulFallback implements FallbackProvider{

	@Override
	public String getRoute() {
		// 为哪个微服务提供回退功能
		// 这样配置所有调用都支持，也可以配置指定的路由回退。service-id: user-service
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		 return new ClientHttpResponse() {
	            @Override
	            public HttpStatus getStatusCode() throws IOException {
	                return HttpStatus.INTERNAL_SERVER_ERROR;
	            }

	            @Override
	            public String getStatusText() throws IOException {
	                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
	            }

	            @Override
	            public void close() {
	            }
	           
	            @Override
	            public InputStream getBody() throws IOException {
	            	//定义自己的错误信息
	                return new ByteArrayInputStream(("microservice error").getBytes());
	            }

	            @Override
	            public HttpHeaders getHeaders() {
	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.APPLICATION_JSON);
	                return headers;
	            }

				@Override
				public int getRawStatusCode() throws IOException {
					return HttpStatus.INTERNAL_SERVER_ERROR.value();
				}
	        };

	}

}
