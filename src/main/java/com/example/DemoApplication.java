package com.example;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Jwts;

@SpringBootApplication
public class DemoApplication {
	public static SecretKey key = Jwts.SIG.HS256.key().build();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
