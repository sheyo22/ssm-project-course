package org.example.crm.workbench.controller;

import org.example.crm.exception.DeleteException;
import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.utils.UUIDUtil;
import org.example.crm.vo.ReviseDomainVO;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviseVO;
import org.example.crm.workbench.domain.Activity;
import org.example.crm.workbench.domain.ActivityRemark;
import org.example.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public ReviseVO doDeleteActivities(String[] ids) throws DeleteException {
        return activityService.deleteActivities(ids);
    }
    @RequestMapping("/workbench/activity/editActivity.do")
    @ResponseBody
    public Map<String, Object> doEditActivity(String activityId){
        return activityService.getEditInfo(activityId);
    }
    @RequestMapping("/workbench/activity/updateActivity.do")
    @ResponseBody
    public ReviseVO doUpdateActivity(Activity activity, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        activity.setEditBy(user.getName());
        return activityService.updateActivity(activity);
    }
    @RequestMapping("/workbench/activity/getActivityInfo.do")
    public ModelAndView getActivityInfo(String activityId){
        ModelAndView re = new ModelAndView();
        re.addObject("activity",activityService.getActivityInfo(activityId));
        re.setViewName("/workbench/activity/detail.jsp");
        return re;
    }
    @RequestMapping("/workbench/activity/getActivityRemarkList.do")
    @ResponseBody
    public List<ActivityRemark> getActivityRemarkList(String activityId){
        return activityService.getActivityRemarkList(activityId);
    }
    @RequestMapping("/workmarch/activity/deleteActivityRemark.do")
    @ResponseBody
    public ReviseVO deleteActivityRemark(String id){
        return activityService.deleteActivityRemark(id);
    }
    @RequestMapping("/workmarch/activity/addActivityRemark.do")
    @ResponseBody
    public ReviseDomainVO addActivityRemark(ActivityRemark activityRemark, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        activityRemark.setCreateBy(user.getName());
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setId(UUIDUtil.getUUID());
        return activityService.saveActivityRemark(activityRemark);
    }
    @RequestMapping("/workbench/activity/editActivityRemark.do")
    @ResponseBody
    public ReviseDomainVO editActivityRemark(ActivityRemark activityRemark, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        activityRemark.setEditBy(user.getName());
        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        activityRemark.setEditFlag("1");
        return activityService.updateActivityRemark(activityRemark);
    }
}
