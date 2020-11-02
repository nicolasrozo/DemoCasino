package com.nirobe.springboot.Casinodemo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nirobe.springboot.Casinodemo.model.User;
import com.nirobe.springboot.Casinodemo.repository.UserRepository;

@SpringBootApplication
public class CasinodemoApplication {

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CasinodemoApplication.class, args);

	}
	
	@Bean
	InitializingBean initData() {
       return () -> {
    	   userRepository.save(new User(10000));
         };
	}

}
