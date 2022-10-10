package com.project.UserManagementService.repository;


import com.project.UserManagementService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User,Integer> {

    User findByUserName(String userName);
}