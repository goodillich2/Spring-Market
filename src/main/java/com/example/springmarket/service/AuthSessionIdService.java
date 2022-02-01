package com.example.springmarket.service;


import com.example.springmarket.model.user.AuthSessionId;
import com.example.springmarket.model.user.User;
import com.example.springmarket.repository.AuthSessionIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthSessionIdService {

    @Autowired
    AuthSessionIdRepository authSessionIdRepository;

    @Autowired
    UserService userService;

    public User getUser(String session) throws Exception {
         AuthSessionId authSessionId =  authSessionIdRepository.findBySessionId(session);
         User user = authSessionId.getUser();
         if(user.equals(null)){
             throw new Exception("User is null");
         }
         return user;
    }

    public void save(User user, String sessionId) {
        AuthSessionId authSessionId = new AuthSessionId();
        authSessionId.setSessionId(sessionId);
        authSessionId.setUser(user);
        authSessionIdRepository.save(authSessionId);
    }

    public boolean findBySessionId(String sessionId) {
        AuthSessionId authSessionId =  authSessionIdRepository.findBySessionId(sessionId);
        if(authSessionId == null){
            return false;
        }
        return true;
    }
}
