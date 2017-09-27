package com.siran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;

@Configuration
@EnableAutoConfiguration

@SpringBootApplication
@RestController
@EnableWebMvc
@EnableAuthorizationServer
@EnableResourceServer
@Import({ApplicationConfig.class, OAuth2Config.class, SecurityConfig.class})
public class Application {


    @RequestMapping("/user/user")
//		@PreAuthorize("#hasRole('ROLE_CLIENT')")
    public Principal user(Principal principal) {

        final String userName = principal.getName();

//			userDao.getUserIdByName(userName);
//			userDao.getUser(userName);


        System.out.println("user.getName():" + userName);
        return principal;
    }

    @RequestMapping("/user1")
    public Principal user1(Principal principal) {

        final String userName = principal.getName();

        System.out.println("user.getName():" + userName);
        return principal;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
