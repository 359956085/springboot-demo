package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
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
		if (userService.addUser(user)) {
			map.put("error_code", "0");
			map.put("id", user.getUserId());
		} else {
			map.put("error_code", "1000");
		}
		return map;
	}
	
	
}
