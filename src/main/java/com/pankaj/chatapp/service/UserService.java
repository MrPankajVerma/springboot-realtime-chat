package com.pankaj.chatapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pankaj.chatapp.entity.Status;
import com.pankaj.chatapp.entity.User;
import com.pankaj.chatapp.repository.UserRepository;
import com.pankaj.chatapp.security.JwtService;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); 		//encrypt
		user.setStatus(Status.OFFLINE);
		return userRepository.save(user);
	}
	
	public String login(String email, String password) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		
		User user = userOptional.orElseThrow(() -> new RuntimeException("User not found!"));
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid Password!");
		}
		
		user.setStatus(Status.ONLINE);
		
		userRepository.save(user);
		return jwtService.generateToken(user.getEmail());
	}
}
