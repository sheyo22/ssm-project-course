package org.example.crm.settings.service;

import org.example.crm.exception.LoginException;
import org.example.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
