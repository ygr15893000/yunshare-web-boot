package com.siran;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@ComponentScan({ "com.siran" })
public class ApplicationConfig {
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new StandardPasswordEncoder();
//                new BCryptPasswordEncoder();
		return encoder;
	}
}
