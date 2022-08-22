package com.usermanagement.app.handler;

public class UserHelper {
	
	public static boolean isNullOrBlank(String input) {
		if(input.isBlank() || null == input) {
			return true;
		}
		return false;
	}

}
