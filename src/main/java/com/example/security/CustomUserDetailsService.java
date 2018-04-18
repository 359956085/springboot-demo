package com.example.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.entity.User;
import com.example.service.UserService;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username);
		if (user == null) {
            throw new UsernameNotFoundException("用户名：" + username + "不存在");
		}
		//写入用户的角色  由于框架原因 角色名称要以 ROLE_ 开头
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_user");
		authorities.add(authority);
		user.setAuthorities(authorities);
		return user;
	}

}
