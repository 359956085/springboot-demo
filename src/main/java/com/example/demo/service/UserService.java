package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}
	
	public boolean addUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		return 1 == userMapper.addUser(user) ? true : false;
	}
}
