package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.User;

@Mapper
public interface UserMapper {

	User findUserById(Integer id);
	User findUserByUsername(String username);
	int addUser(User user);
	int updateUserById(User user);
}
