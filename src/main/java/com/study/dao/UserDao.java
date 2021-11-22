package com.study.dao;

import com.study.domain.User;

public interface UserDao {
    User getUserByEmail(String email);

    Integer saveUser(User user);
}
