package com.example.demo.service;

import com.example.demo.Classes.UserClasses.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void  createUser(User user) { userRepository.save(user); }

    public List<User> readUser(Long userid){
        List<User> us=new ArrayList<>();
        userRepository.findById(userid).ifPresent(us::add);
        return us;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long userid){
        userRepository.deleteById(userid);
    }

}
