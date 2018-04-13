package com.example.demo.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence arg0) {
		return BCrypt.hashpw(arg0.toString(), BCrypt.gensalt());
	}

	@Override
	public boolean matches(CharSequence arg0, String arg1) {
		return BCrypt.checkpw(arg0.toString(), arg1);
	}

}
