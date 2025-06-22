package com.wmx.jdk17app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 1、注意：dynamic-datasource V3.3.3及以上版本不用再排除原生的Druid快速配置类。
 * 2、某些SpringBoot的版本可能在启动类上无法使用注解的方式排除，此时可以在配置文件中进行排除，如：
 * * spring.autoconfigure.exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
 */
// @SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@SpringBootApplication
public class Jdk17appApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jdk17appApplication.class, args);
    }

}
