package com.pivo.hotelo.domain.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private UserRoles roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if(roles == UserRoles.ADMIN) 
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_HOTEL"),new SimpleGrantedAuthority("ROLE_CLIENT"));
		
		if(roles == UserRoles.HOTEL) 
			return List.of(new SimpleGrantedAuthority("ROLE_HOTEL"));
		
		else {
			return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
		}
		
	}

	@Override
	public String getPassword() {
		
		return senha;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
}
