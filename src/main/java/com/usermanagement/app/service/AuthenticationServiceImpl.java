package com.usermanagement.app.service;

import java.time.Instant;
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
		
        User user = userRepository.findItemByEmail(email);
        if (null == user) {
            throw new ResourceNotFoundException("User with email not found.");
        }
        
        byte[] decodedBytePassword = Base64.getDecoder().decode(user.getPassword());
        String decodedPassword = new String(decodedBytePassword);
        
        if(user.getEmail().equals(email) && decodedPassword.equals(password)) {
            
         // Here user is trying to login and hence login time changes.
            user.setLoginTime(Instant.now());
            userRepository.save(user);
            
        	return true;
        } else {
        	return false;
        }
	}

}
