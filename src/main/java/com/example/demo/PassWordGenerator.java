package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassWord = "123456";
		String encodePassWord = encoder.encode(rawPassWord);
		System.out.println(encodePassWord);

	}

}

