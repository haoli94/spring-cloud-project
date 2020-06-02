package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
// @EnableEurekaServer 表明自己是服务注册中心，提供服务的发现, 配置, 注册
public class EurekaMain7003 {
  public static void main(String[] args) {
    SpringApplication.run(EurekaMain7003.class, args);
  }
}


// Eureka 属于 CAP 里的 AP分支, 某个时刻某个服务不可用了, Eureka 不会对其立即清理, 依旧会保留信息