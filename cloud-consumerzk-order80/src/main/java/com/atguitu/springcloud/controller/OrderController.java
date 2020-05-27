package com.atguitu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
public class OrderController {

  public static final String PAYMENT_SERVICE = "http://cloud-payment-service"; //集群

  @Value("${server.port}")
  private String serverPort;

  @Resource
  private RestTemplate restTemplate;

  @GetMapping(value = "consumer/payment/zk")
  public String PaymentZk() {
    String endPoint = "/payment/zk";
    log.info(PAYMENT_SERVICE + endPoint);
    log.info("This is order service serving at port: " + serverPort);
    return restTemplate.getForObject(PAYMENT_SERVICE + endPoint, String.class);
  }
}
