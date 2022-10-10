package com.project.UserManagementService.service;

import com.project.UserManagementService.model.User;
import com.project.UserManagementService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.UserManagementService.model.User.SEQUENCE_NAME;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    public UserRepository userRepository;


    @Autowired
    public SequenceGeneratorService sequenceGeneratorService;



    @Override
    public User add(User user)
    {
        user.setUserId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
        return userRepository.save(user);
    }


    @Override
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> viewbyId(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(int userId)
    {
        userRepository.deleteById(userId);
    }


    @Override
    public User updateUser(int userId, User user) {
        Optional<User> findById = userRepository.findById(userId);
        if (findById.isPresent()) {
            User adminEntity = findById.get();
            if (user.getUserName() != null && !user.getUserName().isEmpty())
                adminEntity.setUserName(user.getUserName());
            if (user.getPassword() != null && !user.getPassword().isEmpty())
                adminEntity.setPassword(user.getPassword());
            if (user.getEmail() != null && !user.getEmail().isEmpty())
                adminEntity.setEmail(user.getEmail());
            if (user.getPhone() != null && !user.getPhone().isEmpty())
                adminEntity.setPhone(user.getPhone());

            return userRepository.save(adminEntity);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User findAdmin = userRepository.findByUserName(username);
        if(findAdmin==null) {
            return null;
        }
        else {
            String name=findAdmin.getUserName();
            String pwd = findAdmin.getPassword();
            return new org.springframework.security.core.userdetails.User(name,pwd,new ArrayList<>());
        }
    }




}
