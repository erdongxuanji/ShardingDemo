package com.example.ShardingDemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ShardingDemo.model.Order;
import com.example.ShardingDemo.model.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    // 这里要写逻辑表名, 并不是实际的表名
    // 分库的联表查询
    @Select({"SELECT o.order_no, SUM(i.price * i.count) AS amount FROM t_order o JOIN t_order_item i ON o.order_no = i.order_no GROUP BY o.order_no"})
    List<OrderVo> getOrderAmount();
}
