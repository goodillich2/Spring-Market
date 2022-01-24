package com.example.springmarket.controller;

import com.example.springmarket.model.user.User;
import com.example.springmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('read')")
    public List<User> getList(){
        return userService.getAll();
    }


}
