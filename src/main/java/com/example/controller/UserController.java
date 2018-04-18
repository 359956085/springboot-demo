package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private static Logger log = LogManager.getLogger(UserController.class);
	@Autowired UserService userService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public User getUser(Integer id) {
		return userService.findUserById(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addUser(User user) {
		Map<String, Object> map = new HashMap<>();
		try {
			userService.addUser(user);
			map.put("error_code", "0");
			map.put("id", user.getUserId());
			
		} catch (Exception e) {
			map.put("error_code", "1000");
			log.error(e);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public Map<String, Object> updateUser(User user) {
		Map<String, Object> map = new HashMap<>();
		try {
			userService.updateUser(user);
			map.put("error_code", "0");
			map.put("id", user.getUserId());
			
		} catch (Exception e) {
			map.put("error_code", "1000");
			log.error(e);
		}
		return map;
	}
	
	
}
