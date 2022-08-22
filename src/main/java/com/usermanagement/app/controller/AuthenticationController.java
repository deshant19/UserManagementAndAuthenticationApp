package com.usermanagement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.app.handler.UserHelper;
import com.usermanagement.app.model.User;
import com.usermanagement.app.service.AuthenticationService;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationService authenticationService;

	@PostMapping(path = "/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {
		
		if (UserHelper.isNullOrBlank(user.getPassword()) || UserHelper.isNullOrBlank(user.getEmail())) {
            throw new IllegalArgumentException("User email or password must not be blank.");
        } 
    	
        if(authenticationService.authenticateUser(user.getEmail(), user.getPassword())) {
        	return new ResponseEntity<String>("Login Successful.", HttpStatus.OK);
        } else {
        	return new ResponseEntity<String>("Unable to login. Wrong password.", HttpStatus.UNAUTHORIZED);
        }
    }
}
