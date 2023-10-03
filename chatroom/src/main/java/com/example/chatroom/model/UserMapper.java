package com.example.chatroom.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //针对注册功能将用户信息插入到数据库中
    public int insert(User user);
    //根据用户名查询用户信息 -> 登录
    User selectByname(String username);
}
