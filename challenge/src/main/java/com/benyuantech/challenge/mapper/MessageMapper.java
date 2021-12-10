package com.benyuantech.challenge.mapper;

import com.benyuantech.challenge.model.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    int insert(Message message);// 添加留言，这是实际使用的方法
    int insertSelective(Message message);// 添加留言
}
