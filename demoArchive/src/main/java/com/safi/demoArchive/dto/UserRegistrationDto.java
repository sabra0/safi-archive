package com.safi.demoArchive.dto;

import java.util.Collection;

import com.safi.demoArchive.entities.Role;


public class UserRegistrationDto {
	
	private String email;
	private String password;
	private Collection<Role> roles;
	
	public UserRegistrationDto() {
		
	}
	
	public UserRegistrationDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
	

}
