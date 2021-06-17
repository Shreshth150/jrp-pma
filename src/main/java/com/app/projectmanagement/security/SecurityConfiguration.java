package com.app.projectmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username, password, enabled "+
				"from user_accounts where username = ?" )
			.authoritiesByUsernameQuery("select username, role "+
				"from user_accounts where username = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptEncoder);
		
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests()
////			.antMatchers("/projects/new").hasRole("ADMIN")
////			.antMatchers("/projects/save").hasRole("ADMIN")
////			.antMatchers("/employees/new").hasRole("ADMIN")
////			.antMatchers("/employees/save").hasRole("ADMIN")
//			.antMatchers("/", "/**").permitAll()
//			.and()
//			.formLogin();
//
//		http.csrf().disable();
//
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/projects/**","/employees/**","/home").authenticated()
				.antMatchers("/", "/**").permitAll()
				.and()
				.formLogin().defaultSuccessUrl("/home");
//		http.csrf().disable();
	}



	
}
