package org.spring.hibernate.auth;


/**
 * @program: spring-cloud-leezy
 * @description: Spring Security 测试类
 * @author: LEEZY
 * @create: 2019-03-28 10:15
 **/

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {

    /*public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("root").password("123456").roles("USER")
                .and()
                .withUser("ADMIN").password("ADMIN").roles("ADMIN");
    }

    private static String REALM = "MY SPRING SECURITY DEMO";

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                // 对于/rest 路径下的访问都需要 ROLE_ADMIN的权限
                .antMatchers("/rest/**").authenticated()
                // 对/user 路径开放访问
                .antMatchers("/user/**").permitAll()
                // 其他url路径访问之前需要登录
                .anyRequest().authenticated()
                //.anyRequest().authenticated()
                .and()
                // 启用basic authentication
                .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthenticationEntryPoint())
                .and()
                // 不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BasicAuthenticationEntryPoint getBasicAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName(REALM);
        return entryPoint;
    }

    // 开放Option请求
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }*/
}
