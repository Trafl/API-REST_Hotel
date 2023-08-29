package com.pivo.hotelo.core.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pivo.hotelo.domain.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository repository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var tokenJWT = token(request);
		
		if(tokenJWT != null) {
			
			var subject = tokenService.verifyTokenAndGetSubjetc(tokenJWT);
			var user = repository.findByEmail(subject);
			
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String token(HttpServletRequest request) {
		
		var authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}
	
}
