package com.example.demo.mapper;

import com.example.demo.entity.User;

public interface UserMapper {

	User findUserById(Integer id);
	User findUserByUsername(String username);
	int addUser(User user);
}
