package com.pankaj.chatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pankaj.chatapp.entity.Status;
import com.pankaj.chatapp.entity.User;
import com.pankaj.chatapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); 		//encrypt
		user.setStatus(Status.OFFLINE);
		return userRepository.save(user);
	}
}
