package org.example.crm.workbench.dao;

import org.example.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int getCount(String[] ids);
    int delete(String[] ids);
    int deleteById(String id);
    List<ActivityRemark> select(String id);
    int insertActivityRemark(ActivityRemark activityRemark);
    int updateActivityRemark(ActivityRemark activityRemark);
}
