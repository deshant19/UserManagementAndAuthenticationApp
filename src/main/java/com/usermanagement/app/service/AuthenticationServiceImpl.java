package com.usermanagement.app.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.app.exception.ResourceNotFoundException;
import com.usermanagement.app.model.User;
import com.usermanagement.app.mongorepository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
    private UserRepository userRepository;

	@Override
	public boolean authenticateUser(String email, String password) {
		
		if (email == null || password == null) {
            throw new IllegalArgumentException("Email or password must not be null.");
        }

        User user = userRepository.findItemByEmail(email);
        if (user.equals(null)) {
            throw new ResourceNotFoundException("User not found");
        }
        
        byte[] decodedBytePassword = Base64.getDecoder().decode(user.getPassword());
        String decodedPassword = new String(decodedBytePassword);
        
        if(user.getEmail().equals(email) && decodedPassword.equals(password)) {
        	return true;
        } else {
        	return false;
        }
	}

}
