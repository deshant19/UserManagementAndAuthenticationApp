package com.usermanagement.app.service;

import com.usermanagement.app.model.User;

public interface UserService {
	
	User addUser(User user);

	User updateUser(User user);

    void deleteUser(String name);

    User getUserByName(String name);

    User getAllUsers();

}
