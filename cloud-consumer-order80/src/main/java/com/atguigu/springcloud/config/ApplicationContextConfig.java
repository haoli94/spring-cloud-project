package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// RestTemplate需要注入
@Configuration
public class ApplicationContextConfig {
  // public static final String PAYMENT_SERVICE = "http://CLOUD-PAYMENT-SERVICE";
  // 通过微服务名称调用, 一个微服务名称下面注册了多个服务节点
  // 集群不知道调哪个服务要开load balance才行, round robin之类的随机选择返回
  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
