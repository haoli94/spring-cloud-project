package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
  public String PaymentInfo_OK(Integer id) {
    int timeout = 100;
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "ThreadPool " + Thread.currentThread().getName() + " PaymentInfo_OK, id = " + id + "\t";
  }

  @HystrixCommand(
      fallbackMethod = "PaymentInfoTimeoutHandler",
      commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
      })
  public String PaymentInfo_Timeout(Integer id) {
    int timeout = 3000;
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "ThreadPool "
        + Thread.currentThread().getName()
        + " PaymentInfo_Timeout = "
        + timeout
        + ", id = "
        + id
        + "\t";
  }
  public String PaymentInfoTimeoutHandler(Integer id) {
    return "Threadpool " + Thread.currentThread().getName() + "id: " + id + " PaymentInfoTimeoutHandler, -_- ...";
  }
}
