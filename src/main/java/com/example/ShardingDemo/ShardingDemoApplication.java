package com.example.ShardingDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ShardingDemo.dao")
public class ShardingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDemoApplication.class, args);
	}

}
