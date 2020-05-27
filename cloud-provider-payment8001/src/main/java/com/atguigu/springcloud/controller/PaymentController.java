package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j // 日志打印
public class PaymentController {
  @Autowired private PaymentService paymentService;

  @Value("${server.port}")
  private String serverPort;

  @Resource private DiscoveryClient discoveryClient;

  // 之前在SpringMVC是requestMapping 这里可以写细致一点
  @PostMapping(value = "/payment/create") // 调用是Post方法
  // 微服务, 外面调用 @RequestBody
  public CommonResult create(@RequestBody Payment payment) {
    int result = paymentService.create(payment);
    log.info("insert result + " + result);
    if (result > 0) {
      return new CommonResult(200, "Yes, insert into database, serverPort: " + serverPort, result);
    } else {
      return new CommonResult(444, "Opps, insert failed.", null);
    }
  }

  // 直接 call
  //  public CommonResult create(Payment payment) {
  //    int result = paymentService.create(payment);
  //    log.info("insert result + " + result);
  //    if (result > 0) {
  //      return new CommonResult(200, "Yes, insert into database.", result);
  //    } else {
  //      return new CommonResult(444, "Opps, insert failed.", null);
  //    }
  //  }

  @GetMapping(value = "/payment/get/{id}") // 调用是Get方法
  // 外面调用 @requestBody
  public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
    Payment payment = paymentService.getPaymentById(id);
    log.info("find result + " + payment);
    if (payment != null) {
      return new CommonResult(
          200, "Yes, haha, find payment record in database, serverPort: " + serverPort, payment);
    } else {
      return new CommonResult(
          444, "Opps, oh no, no such payment record in database with id: " + id + ".", null);
    }
  }

  @GetMapping(value = "/payment/discovery")
  public Object discovery() {
    List<String> services = discoveryClient.getServices();
    for (String service : services) {
      log.info("*******element: " + service);
    }
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
      log.info(
          "*******instance: "
              + instance.getHost()
              + "\t"
              + instance.getInstanceId()
              + "\t"
              + instance.getPort()
              + "\t"
              + instance.getUri());
    }
    return this.discoveryClient;
  }
}
