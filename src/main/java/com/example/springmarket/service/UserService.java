package com.example.springmarket.service;

import com.example.springmarket.model.user.User;
import com.example.springmarket.repository.UserRepository;
import com.example.springmarket.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiResponse signUp(User user) {
        ApiResponse response = new ApiResponse(true, "success response");
        return response;
    }
    public List<User> getAll(){return userRepository.findAll();}
}
