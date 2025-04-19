package com.example.ShardingDemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@TableName("t_order_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    //当配置了shardingsphere-jdbc的分布式序列时，自动使用shardingsphere-jdbc的分布式序列
    //没测，感兴趣可以自己测下，配置这个到底是mysbatis-plus 还是 shardingsphere-jdbc 生成的
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal price;
    private Integer count;
}
