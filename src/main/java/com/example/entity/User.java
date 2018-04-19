package com.example.entity;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class User implements UserDetails, Serializable{

	private static final long serialVersionUID = -6177197232087121632L;
	
	@Id
	@Nullable
	Integer userId;
	@Nullable
	String username;
	@Nullable
	String password;
	@Nullable
	String name;
	@Nullable
	Integer age;
	@Nullable
	Boolean isAccountNonExpired;
	@Nullable
	Boolean isAccountNonLocked;
	@Nullable
	Boolean isCredentialsNonExpired;
	@Nullable
	Boolean isEnabled;
	@Nullable
	Collection<GrantedAuthority> authorities;
	
	public User() {
		
	}
	
	public User(Integer userId, String username, String password, String name, Integer age, Boolean isAccountNonExpired,
			Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, Boolean isEnabled,
			Collection<GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.age = age;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}


	
}
