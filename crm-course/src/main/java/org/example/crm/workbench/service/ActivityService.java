package org.example.crm.workbench.service;

import org.example.crm.exception.DeleteException;
import org.example.crm.vo.ReviseDomainVO;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviseVO;
import org.example.crm.workbench.domain.Activity;
import org.example.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveActivity(Activity activity);
    PaginationVO<Activity> selectPageList(Activity activity,int skipCount,int pageSize);
    ReviseVO deleteActivities(String[] id) throws DeleteException;
    Map<String, Object> getEditInfo(String activityId);
    ReviseVO updateActivity(Activity activity);
    Activity getActivityInfo(String activityId);
    List<ActivityRemark> getActivityRemarkList(String activityId);
    ReviseVO deleteActivityRemark(String id);
    ReviseDomainVO saveActivityRemark(ActivityRemark activityRemark);
    ReviseDomainVO updateActivityRemark(ActivityRemark activityRemark);
}
