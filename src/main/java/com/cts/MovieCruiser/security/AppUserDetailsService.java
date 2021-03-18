package com.cts.MovieCruiser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.MovieCruiser.User;
import com.cts.MovieCruiser.repository.UserRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user=userRepository.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found!");
		}
		
		AppUser appUser = new AppUser(user);
		return appUser;
		
	}

}