package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
  @Resource private PaymentService paymentService;

  @GetMapping("/payment/hystrix/ok/{id}")
  public String PaymentInfo_OK(@PathVariable("id") Integer id) {
    String res = paymentService.PaymentInfo_OK(id);
    log.info("****** Result = " + res);
    return res;
  }

  @GetMapping("/payment/hystrix/timeout/{id}")
  public String PaymentInfo_Timeout(@PathVariable("id") Integer id) {
    String res = paymentService.PaymentInfo_Timeout(id);
    log.info("****** Result = " + res);
    return res;
  }
}
