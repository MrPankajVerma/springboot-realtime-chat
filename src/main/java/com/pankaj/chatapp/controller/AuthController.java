package com.pankaj.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.chatapp.dto.LoginRequest;
import com.pankaj.chatapp.entity.User;
import com.pankaj.chatapp.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		return userService.login(request.getEmail(), request.getPassword());
	}
}
