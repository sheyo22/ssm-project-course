package org.example.crm.workbench.service.impl;

import org.example.crm.settings.dao.UserDao;
import org.example.crm.settings.domain.User;
import org.example.crm.workbench.dao.ActivityDao;
import org.example.crm.workbench.service.ActivityService;

import javax.annotation.Resource;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;
}
