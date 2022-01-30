package com.example.springmarket.model.user;


import javax.persistence.*;

@Entity
@Table(name = "auth_session_id")
public class AuthSessionId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "session_id")
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthSessionId(String sessionId, User user) {
        this.sessionId = sessionId;
        this.user = user;
    }

    public AuthSessionId(){

    }
}
