package com.gnnu.service.impl;

import com.gnnu.dao.UserDao;
import com.gnnu.entity.User;
import com.gnnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }
}
