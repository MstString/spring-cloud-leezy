package org.spring.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 由于mapper接口处写了@Mapper就无需自己再写@MapperScanner就可以实现Bean的注入
// @MapperScan("org.spring.cloud.service")
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }
}
