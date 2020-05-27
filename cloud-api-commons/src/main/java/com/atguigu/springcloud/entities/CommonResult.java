package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 和前端通用的结果集
public class CommonResult<T> {
  // T 通用可以装不同的Payment 和 Order
  // 404, 200
  private Integer code;
  private String message;
  private T data;

  public CommonResult(Integer code, String message) {
    this(code, message, null);
  }
}
