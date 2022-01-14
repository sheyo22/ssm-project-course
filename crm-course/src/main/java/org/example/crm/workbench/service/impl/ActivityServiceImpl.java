package org.example.crm.workbench.service.impl;

import org.example.crm.exception.DeleteException;
import org.example.crm.exception.UpdateException;
import org.example.crm.settings.dao.UserDao;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.vo.ReviseDomainVO;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviseVO;
import org.example.crm.workbench.dao.ActivityDao;
import org.example.crm.workbench.dao.ActivityRemarkDao;
import org.example.crm.workbench.domain.Activity;
import org.example.crm.workbench.domain.ActivityRemark;
import org.example.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;
    @Resource
    private ActivityRemarkDao activityRemarkDao;
    @Resource
    private UserDao userDao;

    @Override
    public int saveActivity(Activity activity) {
        return activityDao.insertActivity(activity);
    }

    @Override
    public PaginationVO<Activity> selectPageList(Activity activity, int skipCount, int pageSize) {
        PaginationVO<Activity> res = new PaginationVO<>();
        List<Activity> pageList = activityDao.selectPageList(activity,skipCount,pageSize);
        res.setPageList(pageList);
        res.setTotal(activityDao.totalCount(activity));
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = DeleteException.class)
    public ReviseVO deleteActivities(String[] ids) throws DeleteException{
        int remakeCount = activityRemarkDao.getCount(ids);
        boolean success = activityRemarkDao.delete(ids)==remakeCount;
        if(!success){
            throw new DeleteException("删除评论错误");
        }
        success = activityDao.delete(ids) == ids.length;
        if(!success){
            throw new DeleteException("删除市场活动错误");
        }
        ReviseVO res = new ReviseVO();
        res.setSuccess(true);
        res.setTotal(ids.length);
        return res;
    }
    public Map<String,Object> getEditInfo(String activityId){
        Map<String,Object> reMap = new HashMap<>();
        reMap.put("userList",userDao.selectAll());
        reMap.put("activity",activityDao.selectActivity(activityId));
        return reMap;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = UpdateException.class)
    public ReviseVO updateActivity(Activity activity) {
        activity.setEditTime(DateTimeUtil.getSysTime());
        if(!(activityDao.updateActivity(activity)==1)){
            throw new UpdateException("修改数据失败");
        }
        ReviseVO re = new ReviseVO();
        re.setTotal(1);
        re.setSuccess(true);
        return re;
    }

    @Override
    public Activity getActivityInfo(String activityId) {
        return activityDao.selectActivityWithName(activityId);
    }

    @Override
    public List<ActivityRemark> getActivityRemarkList(String activityId) {
        return activityRemarkDao.select(activityId);
    }

    @Override
    public ReviseVO deleteActivityRemark(String id) {
        ReviseVO re = new ReviseVO();
        re.setSuccess(activityRemarkDao.deleteById(id)==1);
        re.setTotal(1);
        return re;
    }

    @Override
    public ReviseDomainVO saveActivityRemark(ActivityRemark activityRemark) {
        ReviseDomainVO re = new ReviseDomainVO();
        if(activityRemarkDao.insertActivityRemark(activityRemark)==1){
            re.setSuccess(true);
            re.setDomain(activityRemark);
        }else {
            throw new RuntimeException();
        }
        return re;
    }

    @Override
    public ReviseDomainVO updateActivityRemark(ActivityRemark activityRemark) {
        ReviseDomainVO re = new ReviseDomainVO();
        re.setSuccess(activityRemarkDao.updateActivityRemark(activityRemark)==1);
        re.setDomain(activityRemark);
        return re;
    }
}
