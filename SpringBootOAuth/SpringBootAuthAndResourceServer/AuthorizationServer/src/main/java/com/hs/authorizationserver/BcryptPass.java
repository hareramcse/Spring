package com.hs.authorizationserver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPass {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		System.out.println(encoder.encode("KiteDanie"));
		System.out.println(encoder.encode("WilliamJohn"));
	}
}
