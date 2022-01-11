package org.example.crm.workbench.controller;

import org.example.crm.exception.DeleteException;
import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.utils.UUIDUtil;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviceVO;
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
        return userService.selectAll();
    }
    @RequestMapping(value = "/workbench/activity/save.do")
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
    @RequestMapping("/workbench/activity/pageList.do")
    @ResponseBody
    public PaginationVO<Activity> doPageList(Activity activity, Integer pageNo, Integer pageSize){
        int skipCount = (pageNo-1)*pageSize;
        return activityService.selectPageList(activity,skipCount,pageSize);
    }
    @RequestMapping("/workbench/activity/deleteActivities.do")
    @ResponseBody
    public ReviceVO doDeleteActivities(String[] ids) throws DeleteException {
        return activityService.deleteActivities(ids);
    }
    @RequestMapping("/workbench/activity/editActivity.do")
    @ResponseBody
    public Map<String, Object> doEditActivity(String activityId){
        return activityService.getEditInfo(activityId);
    }
    @RequestMapping("/workbench/activity/updateActivity.do")
    @ResponseBody
    public ReviceVO doUpdateActivity(Activity activity,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        activity.setEditBy(user.getName());
        return activityService.updateActivity(activity);
    }
}
