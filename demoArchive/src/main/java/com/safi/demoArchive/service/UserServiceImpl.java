package com.safi.demoArchive.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.safi.demoArchive.dto.UserRegistrationDto;
import com.safi.demoArchive.entities.Role;
import com.safi.demoArchive.entities.User;
import com.safi.demoArchive.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
	
	public UserServiceImpl() {
		
	}
	
	 	@Value("${spring.security.user.name}")
	    private String adminUserName;

	    @Value("${spring.security.user.password}")
	    private CharSequence adminPassword;

	    @Value("${spring.security.user.roles}")
	    private String adminRole;
	    

// field based injection:
	@Autowired
	private UserRepository userRepository;
	
// constructor based injection:
	/*
	 * private UserRepository userRepository; public UserServiceImpl(@Lazy
	 * UserRepository userRepository) { super(); this.userRepository =
	 * userRepository; }
	 */
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()),Arrays.asList(new Role("USER")));
		
		
		//User adminUser = new User(adminUserName,passwordEncoder.encode(adminPassword),Arrays.asList(new Role(adminRole)));
		
		return userRepository.save(user);
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("envalid user name or password");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),getAuthorities(user) );
		
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
	
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection < Role > roles){
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}	

}