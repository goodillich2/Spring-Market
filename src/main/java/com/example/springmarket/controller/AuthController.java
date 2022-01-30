package com.example.springmarket.controller;

import com.example.springmarket.model.user.AuthSessionId;
import com.example.springmarket.model.user.User;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthSessionIdService authSessionIdService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage(HttpSession session) throws Exception {
        //мы полчаем сессию
        String sessionId = session.getId();
        //получаем имя юзера
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(currentUserName);
        //получаем обьект юзеря по имени ()
        User user = userService.findByEmail(currentUserName);
        //Сохраняем entity SessionId (authSessionRep.save( User user, String Session ))
        authSessionIdService.save(user,sessionId);

        return "success";
    }
}
