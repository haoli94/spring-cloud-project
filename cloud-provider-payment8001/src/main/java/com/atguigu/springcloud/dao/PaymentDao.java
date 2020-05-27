package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// @Repository 也行但是有时候可能会出问题
@Mapper
public interface PaymentDao {
  public int create(Payment payment);

  public Payment getPaymentById(@Param("id") Long id);
}
