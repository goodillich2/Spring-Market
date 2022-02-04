package com.example.springmarket.controller2;

import com.example.springmarket.model.user.Role;
import com.example.springmarket.model.user.Status;
import com.example.springmarket.model.user.User;
import com.example.springmarket.service.AuthSessionIdService;
import com.example.springmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String getRegistration(@ModelAttribute("newPerson") User user, Model model ){
        if (userService.getUsersFromDB(user)) {
            model.addAttribute("userNameError", "Користувач з таким логіном вже існує в БД");
            return "registration";
        }
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userService.save(user);
        return "redirect:login";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "adminPage";
    }


}
