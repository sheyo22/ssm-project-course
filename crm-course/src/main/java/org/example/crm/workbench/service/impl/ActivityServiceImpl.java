package org.example.crm.workbench.service.impl;

import org.example.crm.settings.dao.UserDao;
import org.example.crm.settings.domain.User;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.workbench.dao.ActivityDao;
import org.example.crm.workbench.domain.Activity;
import org.example.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;

    @Override
    public int saveActivity(Activity activity) {
        return activityDao.insertActivity(activity);
    }
}
