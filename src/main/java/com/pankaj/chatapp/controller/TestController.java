package com.pankaj.chatapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/api/test")
	public String test() {
		String email = (String) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		return "Hello "+email+", Protected API working!";
	}
}
