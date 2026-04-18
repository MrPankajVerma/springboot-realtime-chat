package com.pankaj.chatapp.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey12";
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String email) {
		 return Jwts.builder()
		            .setSubject(email) 
		            .setIssuedAt(new Date())
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 	// 1 hour
		            .signWith(getSignKey())
		            .compact();
	}
	
	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
}
