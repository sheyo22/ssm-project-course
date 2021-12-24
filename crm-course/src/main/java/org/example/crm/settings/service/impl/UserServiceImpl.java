package org.example.crm.settings.service.impl;

import org.example.crm.exception.LoginException;
import org.example.crm.settings.dao.UserDao;
import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd,String ip) throws LoginException {
        List<User> users = userDao.selectUsers(loginAct,loginPwd);
        if(users.size()>1)
            throw new LoginException("出现重复数据");
        if(users.size()==0)
            throw new LoginException("账号或密码不正确");
        User user = users.get(0);
        if("0".equals(user.getLockState()))
            throw new LoginException("该账号已被锁定");
        if(!user.getAllowIps().contains(ip))
            throw new LoginException("不被允许访问的ip地址");
        if(user.getExpireTime().compareTo(DateTimeUtil.getSysTime())<0)
            throw new LoginException("该账号已失效");
        return users.get(0);
    }
}
