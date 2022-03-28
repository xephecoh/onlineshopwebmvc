package com.khamutov.services;

import Utills.PasswordEncryptor;
import com.khamutov.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final UserDao userDao;


    @Autowired
    public UserService( UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(String name,String password){
        String saltedPass = PasswordEncryptor.encrypt(password);
        userDao.saveUser(name,saltedPass);
    }

    public boolean isUserValid(String name,String password){
        String saltedPass = PasswordEncryptor.encrypt(password);
        return userDao.isUserValid(name,saltedPass);
    }
    public String getUserRole(String name){
        return userDao.getUserRole(name);
    }

}
