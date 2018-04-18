package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.cache.RedisKeyPrefix;
import com.example.entity.User;
import com.example.mapper.UserMapper;


@Service
public class UserService {

	@Autowired private RedisTemplate<String, Object> redisTemplate;
	@Autowired UserMapper userMapper;
	
	public User findUserById(Integer id) {
		String key = RedisKeyPrefix.USER + id;
		if (redisTemplate.hasKey(key)) 
			return (User) redisTemplate.opsForValue().get(key);
		User user = userMapper.findUserById(id);
		redisTemplate.opsForValue().set(key, user);
		return user;
	}
	
	public User findUserByUsername(String username) {
		String key = RedisKeyPrefix.USER + username;
		if (redisTemplate.hasKey(key)) 
			return (User) redisTemplate.opsForValue().get(key);
		User user = userMapper.findUserByUsername(username);
		redisTemplate.opsForValue().set(key, user);
		return user;
	}
	
	public User addUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		userMapper.addUser(user);
		return user;
	}
	
	public User updateUser(User user) {
		if (!StringUtils.isEmpty(user.getPassword()))
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		userMapper.updateUserById(user);
		
		redisTemplate.delete(RedisKeyPrefix.USER + user.getUsername());
		redisTemplate.delete(RedisKeyPrefix.USER + user.getUserId());
		return user;
	}
}
