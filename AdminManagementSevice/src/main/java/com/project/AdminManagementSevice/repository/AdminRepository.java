package com.project.AdminManagementSevice.repository;

import com.project.AdminManagementSevice.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


public interface AdminRepository extends MongoRepository<Admin,Integer> {

    Admin findByUserName(String userName);
}