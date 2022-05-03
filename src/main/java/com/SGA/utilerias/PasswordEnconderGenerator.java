package com.SGA.utilerias;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.SGA.SistemaSGASpringBootApiRestApplication;

public class PasswordEnconderGenerator {
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("password"));
	}
}
