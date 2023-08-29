package com.pivo.hotelo.core.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pivo.hotelo.core.security.exception.JWTException;
import com.pivo.hotelo.domain.model.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String tokenGeneration(User user) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    
		    return JWT.create()
		        .withIssuer("Hotelo-API")
		        .withSubject(user.getEmail())
		        .withClaim("id", user.getId())
		        .withExpiresAt(expressTime())
		        .sign(algorithm);
		    
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar token", exception);
		}
	}
	
	public Instant expressTime() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	public String verifyTokenAndGetSubjetc(String tokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			
		    return JWT.require(algorithm)
		        .withIssuer("Hotelo-API")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		        
		} catch (JWTVerificationException exception){
		    throw new JWTException("Token JWT invalido ou expirado.");
		}
	}
}
