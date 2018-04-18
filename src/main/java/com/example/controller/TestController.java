package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.RedisKeyPrefix;
import com.example.entity.User;
import com.example.service.UserService;

@RestController
public class TestController {
	
	@Autowired private StringRedisTemplate stringRedisTemplate;
	@Autowired private RedisTemplate<String, Object> redisTemplate;
	@Autowired UserService userService;
	
	public static void main(String[] args) {
		String s1 = "";
		long l1 = System.currentTimeMillis();
		for(int i=0; i<100_000; i++) {
			s1 = s1 + "a" + "b" + "c" + "d" + "e" + "f";
		}
		System.out.println((System.currentTimeMillis() - l1) + " " + s1.length());  //结果 15041 600000
		
		StringBuilder sb = new StringBuilder();
		String s2;
		long l2 = System.currentTimeMillis();
		for(int i=0; i<100_000; i++) {
			sb.append("a").append("b").append("c").append("d").append("e").append("f");
		}
		s2 = sb.toString();
		System.out.println((System.currentTimeMillis() - l2) + " " + s2.length());  //结果 6 600000
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(Integer id) {
		Object o = redisTemplate.opsForValue().get(RedisKeyPrefix.USER + id);
		System.out.println(o.getClass());
		User user = (User)o;
		System.out.println(user.getName());
		System.out.println(stringRedisTemplate.opsForValue().get(RedisKeyPrefix.USER + id));
	}
}
