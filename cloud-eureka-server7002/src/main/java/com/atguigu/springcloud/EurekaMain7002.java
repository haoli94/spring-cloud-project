package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
// @EnableEurekaServer 表明自己是服务注册中心，提供服务的发现, 配置, 注册
public class EurekaMain7002 {
  public static void main(String[] args) {
    SpringApplication.run(EurekaMain7002.class, args);
  }
}
