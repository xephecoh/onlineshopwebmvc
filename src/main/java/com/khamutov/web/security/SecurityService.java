package com.khamutov.web.security;

import com.khamutov.entities.UserRole;
import com.khamutov.entities.Session;
import com.khamutov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class SecurityService implements Runnable {
    private final UserService userService;
    private final static List<Session> SESSION_LIST = Collections.synchronizedList(new ArrayList<>());

    public SecurityService(@Autowired UserService userService) {
        this.userService = userService;
    }

    public Cookie generateCookie(String userName) {
        UUID uuid = UUID.randomUUID();
        String tokenValue = uuid.toString();
        Session session = new Session(tokenValue, LocalDateTime.now(), getUserRole(userName), userName);
        Cookie cookie = new Cookie("token", tokenValue);
        getSessionList().add(session);
        return cookie;
    }


    public boolean login(String name, String password) {
        return userService.isUserValid(name, password);
    }

    public UserRole getUserRole(String name) {
        return UserRole.valueOf(userService.getUserRole(name));
    }


    public synchronized List<Session> getSessionList() {
        return SESSION_LIST;
    }

    public boolean isTokenPresent(String token) {
        Optional<Session> myToken = getSessionList().stream().filter(e -> e.getTokenValue().equals(token)).findAny();
        return myToken.isPresent();
    }

    private boolean recalculateLfeTime(LocalDateTime creationDateTime) {
        return LocalDateTime.now().minusSeconds(600).isBefore(creationDateTime);
    }

    @Override
    public void run() {
        SESSION_LIST.stream()
                .filter(e -> recalculateLfeTime(e.getCreationTimestamp()))
                .forEach(SESSION_LIST::remove);
        SESSION_LIST.removeIf(session -> recalculateLfeTime(session.getCreationTimestamp()));
    }

    public void recalculateList() {
        SESSION_LIST.stream()
                .filter(e -> recalculateLfeTime(e.getCreationTimestamp()))
                .forEach(SESSION_LIST::remove);
        SESSION_LIST.removeIf(session -> recalculateLfeTime(session.getCreationTimestamp()));
    }


    public Session getSessionByToken(String token) {
        Optional<Session> optionalSession = SESSION_LIST.stream()
                .filter(session -> session.getTokenValue().equals(token))
                .findAny();
        if (optionalSession.isPresent()) {
            return optionalSession.get();
        }
        throw new RuntimeException("No session with such token");
    }
}
