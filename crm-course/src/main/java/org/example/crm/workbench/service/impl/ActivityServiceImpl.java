package org.example.crm.workbench.service.impl;

import org.example.crm.exception.DeleteException;
import org.example.crm.exception.UpdateException;
import org.example.crm.settings.dao.UserDao;
import org.example.crm.settings.domain.User;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviceVO;
import org.example.crm.workbench.dao.ActivityDao;
import org.example.crm.workbench.dao.ActivityRemarkDao;
import org.example.crm.workbench.domain.Activity;
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
    public ReviceVO deleteActivities(String[] ids) throws DeleteException{
        int remakeCount = activityRemarkDao.getCount(ids);
        boolean success = activityRemarkDao.delete(ids)==remakeCount;
        if(!success){
            throw new DeleteException("删除评论错误");
        }
        success = activityDao.delete(ids) == ids.length;
        if(!success){
            throw new DeleteException("删除市场活动错误");
        }
        ReviceVO res = new ReviceVO();
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
    public ReviceVO updateActivity(Activity activity) {
        activity.setEditTime(DateTimeUtil.getSysTime());
        if(!(activityDao.updateActivity(activity)==1)){
            throw new UpdateException("修改数据失败");
        }
        ReviceVO re = new ReviceVO();
        re.setTotal(1);
        re.setSuccess(true);
        return re;
    }
}
