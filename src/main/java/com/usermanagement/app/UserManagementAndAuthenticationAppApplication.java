package com.usermanagement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class UserManagementAndAuthenticationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementAndAuthenticationAppApplication.class, args);
	}

}
