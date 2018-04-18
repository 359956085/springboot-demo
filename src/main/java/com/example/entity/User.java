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
