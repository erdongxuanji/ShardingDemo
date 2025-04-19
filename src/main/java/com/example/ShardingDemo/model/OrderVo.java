package com.example.ShardingDemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderVo {

    // 订单号
    private String orderNo;
    //订单价钱
    private BigDecimal amount;
}