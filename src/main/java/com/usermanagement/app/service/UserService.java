package com.usermanagement.app.service;

import java.util.List;

import com.usermanagement.app.model.User;

public interface UserService {
	
	User addUser(User user);

	User updateUser(User user);

    void deleteUser(String userId);
    
    void deleteUserByName(String name);

    User getUserById(String userId);
    
    List<User> getUserByName(String name);

    List<User> getAllUsers();

	boolean emailExists(String email);

}
