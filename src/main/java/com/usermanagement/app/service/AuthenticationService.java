package com.usermanagement.app.service;

public interface AuthenticationService {

	boolean authenticateUser(String email, String password);
}
