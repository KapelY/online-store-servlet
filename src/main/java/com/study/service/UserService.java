package com.study.service;

import com.study.dao.UserDao;
import com.study.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private UserDao userDao;

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public Integer saveUser(User user) {
        return userDao.saveUser(user);
    }
}
