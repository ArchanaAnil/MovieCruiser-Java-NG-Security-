package com.cts.MovieCruiser.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Bean
//	public AuthenticationProvider authProvider()
//	{
//		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService);
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//		return provider;
//		
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/home")
		.hasRole("Admin")
		.antMatchers("/customer")
		.hasRole("User")
		.antMatchers("/newFavorite/**")
		.hasRole("User")
		.antMatchers("/fav-delete/**")
		.hasRole("User")
		.antMatchers("/favorites")
		.hasRole("User");
		http.formLogin();
		http.addFilter(new JWTAuthFilter(authenticationManager()));


	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 	 //super.configure(auth);
// 	 auth.inMemoryAuthentication()
// 	     .withUser("User")
//	     .password(passwordEncoder().encode("userpwd"))
//	     .roles("USER")
//	     .and()
//	     .withUser("Admin")
//	     .password(passwordEncoder().encode("adminpwd"))
//	     .roles("ADMIN");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	
	@Bean

	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

}
