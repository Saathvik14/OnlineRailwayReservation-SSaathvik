package com.project.UserManagementService.service;

import com.project.UserManagementService.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    User add(User user);

    public List<User> getUsers();

    public Optional<User> viewbyId(int userId);

    public User updateUser(int userId,User user);

    public void deleteUser(int userId);







}
