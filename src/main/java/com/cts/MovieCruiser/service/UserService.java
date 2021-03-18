package com.cts.MovieCruiser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.MovieCruiser.User;
import com.cts.MovieCruiser.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUserByName(String name) {
		
		return userRepository.findByUsername(name);
		
	}
	
}
