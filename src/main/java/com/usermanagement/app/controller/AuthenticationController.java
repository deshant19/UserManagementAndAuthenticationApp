package com.usermanagement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.app.service.AuthenticationService;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationService authenticationService;

	@PostMapping(path = "/authenticate")
    public ResponseEntity<String> authenticateUser(String email, String password) {
        
        if(authenticationService.authenticateUser(email, password)) {
        	return new ResponseEntity<String>("Login Successfull", HttpStatus.OK);
        } else {
        	return new ResponseEntity<String>("Unable to login. Wrong email or password.", HttpStatus.OK);
        }
    }
}
