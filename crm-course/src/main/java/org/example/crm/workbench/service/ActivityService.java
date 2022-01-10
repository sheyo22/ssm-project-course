package org.example.crm.workbench.service;

import org.example.crm.exception.DeleteException;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviceVO;
import org.example.crm.workbench.domain.Activity;

public interface ActivityService {
    int saveActivity(Activity activity);
    PaginationVO<Activity> selectPageList(Activity activity,int skipCount,int pageSize);
    ReviceVO deleteActivities(String[] id) throws DeleteException;
}
