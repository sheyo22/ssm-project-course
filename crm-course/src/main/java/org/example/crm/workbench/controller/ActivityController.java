package org.example.crm.workbench.controller;

import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.utils.UUIDUtil;
import org.example.crm.workbench.domain.Activity;
import org.example.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Resource
    private ActivityService activityService;
    @Resource
    private UserService userService;
    @RequestMapping("/workbench/activity/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        System.out.println("getUserList()");
        return userService.selectAll();
    }
    @RequestMapping("/workbench/activity/save.do")
    @ResponseBody
    public Map doSave(Activity activity, HttpServletRequest request){
        Map re = new HashMap();
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        User user= (User) request.getSession().getAttribute("user");
        activity.setCreateBy(user.getName());
        int success =activityService.saveActivity(activity);
        if(success==1)
            re.put("success",true);
        else
            re.put("success",false);
        return re;
    }
}
