package org.example.crm.workbench.dao;

import org.apache.ibatis.annotations.Param;
import org.example.crm.workbench.domain.Activity;

import java.util.List;

public interface ActivityDao {
    int insertActivity(Activity activity);
    List<Activity> selectPageList(@Param(value = "activity") Activity activity, @Param("skipCount") int skipCount,@Param("pageSize") int pageSize);
    int totalCount(Activity activity);
    int delete(String[] ids);
    Activity selectActivity(String activityId);
    int updateActivity(Activity activity);
}
