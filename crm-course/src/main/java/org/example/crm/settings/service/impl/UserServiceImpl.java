package org.example.crm.settings.service.impl;

import org.example.crm.settings.dao.UserDao;
import org.example.crm.settings.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
}
