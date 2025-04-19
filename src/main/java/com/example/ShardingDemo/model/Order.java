package com.example.ShardingDemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@TableName("t_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    // 这里写成none 因为分布式id 有shardingsphere 生成了
    // 不要写成 long id; 这玩意卡我一天
    @TableId(type = IdType.NONE)
    private Long id =-1L;
    private String orderNo;
    private long userId;
    private BigDecimal amount;
}