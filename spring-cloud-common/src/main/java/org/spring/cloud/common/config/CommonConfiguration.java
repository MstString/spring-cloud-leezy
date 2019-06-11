package org.spring.cloud.common.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.spring.cloud.common.context.SpringCloudHystrixConcurrencyStrategy;
import org.spring.cloud.common.intercepter.FeignUserContextInterceptor;
import org.spring.cloud.common.intercepter.RestTemplateUserContextInterceptor;
import org.spring.cloud.common.intercepter.UserContextInterceptor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import feign.Feign;

@Configuration
@EnableWebMvc
public class CommonConfiguration implements WebMvcConfigurer {
	
	@Autowired
	BeanFactory beanFactory;
	
	// 请求拦截器
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserContextInterceptor());
    }


	// Feign请求拦截器，在发送请求前设置认证的上下文信息
    @Bean
    @ConditionalOnClass(Feign.class)
    public FeignUserContextInterceptor feignTokenInterceptor() {
        return new FeignUserContextInterceptor();
    }

    // RestTemplate拦截器
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RestTemplateUserContextInterceptor());
        return restTemplate;
    }
    
    // 注册固定大小的线程池
    @Bean
    public ExecutorService executorService() {
    	ExecutorService executorService = Executors.newFixedThreadPool(2);
    	return new TraceableExecutorService(this.beanFactory, executorService);
    }
    
    @Bean
	public SpringCloudHystrixConcurrencyStrategy springCloudHystrixConcurrencyStrategy() {
		return new SpringCloudHystrixConcurrencyStrategy();
	}
   
}
