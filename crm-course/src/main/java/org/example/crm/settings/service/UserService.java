package org.example.crm.settings.service;

import org.example.crm.exception.LoginException;
import org.example.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
    List<User> selectAll();
}
