package com.example.ShardingDemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ShardingDemo.model.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper extends BaseMapper<Dict> {
}
