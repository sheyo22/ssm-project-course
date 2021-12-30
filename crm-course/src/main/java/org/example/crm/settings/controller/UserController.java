package org.example.crm.settings.controller;

import org.example.crm.exception.LoginException;
import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/user/login.do")
    @ResponseBody
    public Map login(String loginAct, String loginPwd, HttpServletRequest request) throws LoginException {
        loginPwd = MD5Util.getMD5(loginPwd);
        String ip=request.getRemoteAddr();
        System.out.println("ip:"+ip);
        User user = userService.login(loginAct,loginPwd,ip);
        request.getSession().setAttribute("user",user);
        Map map=new HashMap();
        map.put("msg","true");
        return map;
    }
}
