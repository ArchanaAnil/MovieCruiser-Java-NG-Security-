package com.cts.MovieCruiser.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthFilter extends BasicAuthenticationFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	public JWTAuthFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
			if (token != null) {
         // parse the token. 
			Jws<Claims> jws;
			try {
				jws = Jwts.parser().setSigningKey("ABC123").parseClaimsJws(token.replace("Bearer ", ""));
				String user = jws.getBody().getSubject();
				ArrayList<SimpleGrantedAuthority> arr = new ArrayList<>();
				// Get the role list from UserRepo
				arr.add(new SimpleGrantedAuthority("ROLE_User"));
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, arr);
				}
			} catch (JwtException ex) {
				return null;
			}
			return null;
		}
		return null;
	}
	

}