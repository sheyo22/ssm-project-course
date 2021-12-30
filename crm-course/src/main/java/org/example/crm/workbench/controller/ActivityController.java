package org.example.crm.workbench.controller;

import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ActivityController {
    /*@Resource
    private ActivityService activityService;*/
    @Resource
    private UserService userService;
    @RequestMapping("/workbench/activity/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        return userService.selectAll();
    }
}
