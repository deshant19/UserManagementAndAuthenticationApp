package com.usermanagement.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.app.handler.UserHelper;
import com.usermanagement.app.model.User;
import com.usermanagement.app.service.UserService;


@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
    	if (UserHelper.isNullOrBlank(user.getName()) || UserHelper.isNullOrBlank(user.getPassword()) || UserHelper.isNullOrBlank(user.getEmail())) {
    		return new ResponseEntity<String>("User Name, email or password must not be blank.", HttpStatus.BAD_REQUEST);
        } 
    	
    	if(userService.emailExists(user.getEmail())) {
    		return new ResponseEntity<String>("Email already exists.", HttpStatus.CONFLICT);
    	}
    	
        User userData = userService.addUser(user);
        return ResponseEntity.ok(userData);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateUser(@RequestBody  User user) {
    	if (UserHelper.isNullOrBlank(user.getUserId()) || UserHelper.isNullOrBlank(user.getName()) || 
    			UserHelper.isNullOrBlank(user.getPassword()) || UserHelper.isNullOrBlank(user.getEmail())) {
    		return new ResponseEntity<String>("User Id,  Name, email or password must not be blank.", HttpStatus.BAD_REQUEST);
        } 
    	
    	if(userService.emailExists(user.getEmail())) {
    		return new ResponseEntity<String>("Email already exists.", HttpStatus.CONFLICT);
    	}
    	
        User userData = userService.updateUser(user);
        return ResponseEntity.ok(userData);
    }

    @DeleteMapping(path = "/{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User Id: " + userId + " deleted.", HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/name/{name}/delete")
    public ResponseEntity<String> deleteUserByName(@PathVariable(name = "name") String name) {
        userService.deleteUserByName(name);
        return new ResponseEntity<String>("User Name: " + name + " deleted.", HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(name = "userId") String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable(name = "name") String name) {
        List<User> users = userService.getUserByName(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> wrongPathResponseHandler() {
	    return new ResponseEntity<String>("Wrong request. To get all users try to append /all after user in URL.", HttpStatus.BAD_REQUEST);
	}

}
