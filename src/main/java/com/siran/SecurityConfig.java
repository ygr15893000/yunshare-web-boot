package com.siran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    DataSource dataSource;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery(
					"select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery(
					"select username, role from user_roles where username=?");
	}


	//防止跳到登录页
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.anonymous().disable()
				.authorizeRequests()
				.antMatchers("/oauth/token").permitAll();
	}

	//@ygr 暂时注释 无用
/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests().antMatchers("/login").permitAll().and()
				// default protection for all resources (including /oauth/authorize)
				.authorizeRequests()
				.anyRequest().hasRole("CLIENT1");
		// ... more configuration, e.g. for form login
	}*/
}
