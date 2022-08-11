package com.usermanagement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.app.model.User;
import com.usermanagement.app.service.UserService;


@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userData = userService.addUser(user);
        return ResponseEntity.ok(userData);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<User> updateUser(@RequestBody  User user) {
        User userData = userService.updateUser(user);
        return ResponseEntity.ok(userData);
    }

    @DeleteMapping(path = "/{name}/delete")
    public void deleteUser(@PathVariable(name = "name") String name) {
        userService.deleteUser(name);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<User> getUser(@PathVariable(name = "name") String name) {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<User> getUsers() {
        User users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @RequestMapping (value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> wrongPathResponseHandler() {
	    return new ResponseEntity<String>("Wrong request. To get all users try to append /all after user in URL.", HttpStatus.OK);
	}

}
