package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface PaymentHystrixService {

  @GetMapping("/payment/hystrix/ok/{id}")
  public String PaymentInfo_OK(@PathVariable("id") Integer id);

  @GetMapping("/payment/hystrix/timeout/{id}")
  public String PaymentInfo_Timeout(@PathVariable("id") Integer id);
}
