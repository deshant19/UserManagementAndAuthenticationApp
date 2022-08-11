package com.usermanagement.app.mongorepository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.usermanagement.app.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    @Query("{name:'?0'}")
    List<User> findItemByName(String name);
    
    @Query("{email:'?0'}")
    User findItemByEmail(String email);
    
    public List<User> findAll();

}