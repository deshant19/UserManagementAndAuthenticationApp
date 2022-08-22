package com.usermanagement.app.service;

import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.app.exception.ResourceNotFoundException;
import com.usermanagement.app.model.User;
import com.usermanagement.app.mongorepository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		
	// Here we assume that when a user is added it is considered to be a login
		User userData = new User();
		userData.setLoginTime(Instant.now());
		
	// Encrypt password
		userData.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
		
		userData.setName(user.getName());
		userData.setEmail(user.getEmail());
		
		userData = userRepository.save(userData);
        return userData;
	}

	@Override
	public User updateUser(User user) {
		
		Optional<User> userOpt = userRepository.findById(user.getUserId());
        if (!userOpt.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        User userData = userOpt.get();
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
     // Encrypt password
     	userData.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        
     // Here we assume that when a user updates it is not considered to be a login because user will first login and then update
        userData.setLoginTime(userData.getLoginTime());

        userData = userRepository.save(userData);

        return userData;
	}

	@Override
	public void deleteUser(String userId) {
		
		if (userId == null) {
            throw new IllegalArgumentException("User Id must not be null");
        }
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
	}
	
	@Override
	public void deleteUserByName(String name) {
		
		if (name == null) {
            throw new IllegalArgumentException("User name must not be null");
        }

        List<User> userList = userRepository.findItemByName(name);
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        
        userRepository.deleteAll(userList);
	}

	@Override
	public User getUserById(String userId) {
		
		if (userId == null) {
            throw new IllegalArgumentException("User Id must not be null");
        }

        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        
        User user = userOpt.get();

        User userData = new User();
        userData.setUserId(user.getUserId());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setLoginTime(user.getLoginTime());
        
        return userData;
	}
	
	@Override
	public List<User> getUserByName(String name) {
		
		if (name == null) {
            throw new IllegalArgumentException("User name must not be null");
        }

        List<User> userList = userRepository.findItemByName(name);
        if (userList.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        
        return userList;
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList = userRepository.findAll();
        return userList;
	}

	@Override
	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		for (User user: users) {
            // Checks if the user email is equal to the email parameter
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
		return false;
	}

}
