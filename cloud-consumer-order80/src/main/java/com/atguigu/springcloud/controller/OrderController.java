package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

// 客户端只是去调服务, 只需要一个controller, 也需要实体类payment, 微服务用http调用 RestTemplate

@RestController
@Slf4j
public class OrderController {
// RestTemplate 需要注入容器
//  public static final String PAYMENT_URL = "http://localhost:8001"; 单点
  public static final String PAYMENT_SERVICE = "http://CLOUD-PAYMENT-SERVICE"; //集群
  @Resource // @Autowired
  private RestTemplate restTemplate;

  @GetMapping("consumer/payment/create")
  // 客服端都是发get请求
  public CommonResult<Payment> create(Payment payment){
    String endPoint = "/payment/create";
    return restTemplate.postForObject(PAYMENT_SERVICE + endPoint, payment, CommonResult.class);
  }

  @GetMapping("consumer/payment/get/{id}")
  // 客服端都是发get请求
  public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
    String endPoint = "/payment/get/";
    log.info(PAYMENT_SERVICE + endPoint + id);
    return restTemplate.getForObject(PAYMENT_SERVICE + endPoint + id, CommonResult.class);
  }
}
