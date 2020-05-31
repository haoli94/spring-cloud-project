package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.util.List;

// 客户端只是去调服务, 只需要一个controller, 也需要实体类payment, 微服务用http调用 RestTemplate

@RestController
@Slf4j
public class OrderController {
  // RestTemplate 需要注入容器
  //  public static final String PAYMENT_URL = "http://localhost:8001"; 单点
  public static final String PAYMENT_SERVICE = "http://CLOUD-PAYMENT-SERVICE"; // 集群
  @Resource // @Autowired
  private RestTemplate restTemplate;

  @Resource
  private DiscoveryClient discoveryClient;

  @Resource
  private LoadBalancer loadBalancer;

  @GetMapping("consumer/payment/create")
  // 客服端都是发get请求
  public CommonResult<Payment> create(Payment payment) {
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

  @GetMapping("consumer/payment/getEntity/{id}")
  // 客服端都是发get请求
  public CommonResult getPaymentById2(@PathVariable("id") Long id) {
    String endPoint = "/payment/get/";
    log.info(PAYMENT_SERVICE + endPoint + id);
    ResponseEntity<CommonResult> res =
        restTemplate.getForEntity(PAYMENT_SERVICE + endPoint + id, CommonResult.class);
    if (res.getStatusCode().is2xxSuccessful()) {
      return res.getBody();
    } else {
      return new CommonResult(444, "error");
    }
  }

  @GetMapping("consumer/payment/lb")
  public String getPaymentLB() {
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    if (instances == null || instances.size() < 0){
      return null;
    }
    ServiceInstance instance = loadBalancer.getOneInstance(instances);
    URI uri = instance.getUri();
    return restTemplate.getForObject(uri + "/payment/lb", String.class);
  }
}
