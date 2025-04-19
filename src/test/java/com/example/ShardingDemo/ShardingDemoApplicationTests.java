package com.example.ShardingDemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.ShardingDemo.dao.DictMapper;
import com.example.ShardingDemo.dao.OrderItemMapper;
import com.example.ShardingDemo.dao.OrderMapper;
import com.example.ShardingDemo.dao.UserMapper;
import com.example.ShardingDemo.model.*;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ShardingDemoApplicationTests {

	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderItemMapper orderItemMapper;
	@Resource
	private DictMapper dictMapper;
	@Resource
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	/**
	 * 插入单库单表的user 数据
	 */
	@Test
	void testShardingsphereMysql() {
		for (int i = 0; i < 1; i++) {
			User user = User.builder().name("wadexi00" + (i)).age(32).job("java").city("济南").description("en, very good").build();
			userMapper.insert(user);
		}
		List<User> users = null;
		for (int i = 0; i < 5; i++) {
			users = userMapper.selectList(Wrappers.emptyWrapper());
		}

		System.out.println(users);

	}

	/**
	 * 插入分库也分表的 order，order_item 数据,查询订单和价钱
	 */
	@Test
	void testShardingsphereMysql2() {
		//分库分表插入
		for (int i = 0; i < 4; i++) {
			Order order = Order.builder().orderNo("order_id" + (i)).userId(i).amount(BigDecimal.TEN).build();
			orderMapper.insert(order);
			for (int j = 0; j < 2; j++) {
				OrderItem orderItem = OrderItem.builder().count(2).userId((long) i).price(BigDecimal.TEN).orderNo("order_id" + (i)).build();
				orderItemMapper.insert(orderItem);
			}

		}

	}
	@Test
	void testShardingsphereMysqlsel() {
		//查询单表
		List<Order> orders = null;
		LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.select(Order.class,info->!info.getColumn().equals("order_no"))
				.groupBy(Order::getOrderNo,Order::getId);

		for (int i = 0; i < 2; i++) {
			orders = orderMapper.selectList(lambdaQueryWrapper);
		}
		System.out.println(orders);
		System.out.println(orders.size());

//		//联表 查詢订单号和价钱
//		List<OrderVo> orderAmount = orderMapper.getOrderAmount();
//		System.out.println(orderAmount);
	}
	/**
	 * 广播表，就是每个库里都有一份，不一定去哪个库里查找
	 */
	@Test
	void testShardingsphereMysql3() {
		Dict dict = new Dict();
		dict.setDictType("type1");
		dictMapper.insert(dict);
		List<Dict> dictList = null;
		for (int i = 0; i < 20; i++) {
			dictList = dictMapper.selectList(Wrappers.emptyWrapper());
		}

		dictList.forEach(System.out::println);
	}

}
