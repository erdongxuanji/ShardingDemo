package com.example.ShardingDemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ShardingDemo.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
