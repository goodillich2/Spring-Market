package com.example.springmarket.service;

import com.example.springmarket.model.user.User;
import com.example.springmarket.repository.UserRepository;
import com.example.springmarket.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiResponse signUp(User user) {
        ApiResponse response = new ApiResponse(true, "success response");
        return response;
    }
    public List<User> getAll(){return userRepository.findAll();}

    public User findByEmail(String userEmail) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (!optionalUser.isPresent()) {
            throw new Exception("user not present");
        }
        User user = optionalUser.get();
        return user;
    }
}
