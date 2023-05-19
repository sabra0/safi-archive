package com.safi.demoArchive.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.safi.demoArchive.dto.UserRegistrationDto;
import com.safi.demoArchive.entities.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

}
