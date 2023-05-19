package com.safi.demoArchive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.safi.demoArchive.service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	
	private UserService userService;
	public SecurityConfiguration(@Lazy UserService userService) {
		super();
		this.userService = userService;
	}
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.userDetailsService(userService).passwordEncoder(passwordEncoder()); }
	 */
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		
		http.httpBasic().and().headers().frameOptions().sameOrigin().and()
		. authorizeHttpRequests((requests) -> requests.requestMatchers(
				"/registration**",
                "/",
                "/api/orders/**",
                "/detailsPage/**").permitAll() //urls that doesn't required login.
				//.requestMatchers("/admin/**").hasRole("ADMIN") // Restrict access to URLs that start with /admin/ to only users with the ADMIN role. 
		.anyRequest().authenticated())
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
        .defaultSuccessUrl("/", true) // after login any user will be redirected to this page
		.permitAll()
		.and()
		.logout()	
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/?logout") // after logout redirect to this url
		.deleteCookies("my-remember-me-cookie")
		.permitAll()
		;
		
       return http.build();

	}


}