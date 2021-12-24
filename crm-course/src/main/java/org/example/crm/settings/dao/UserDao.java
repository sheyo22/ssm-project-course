package org.example.crm.settings.dao;

import org.apache.ibatis.annotations.Param;
import org.example.crm.settings.domain.User;

import java.util.List;

public interface UserDao {
    List<User> selectUsers(@Param("loginAct") String loginAct, @Param("loginPwd") String loginPwd);
}
