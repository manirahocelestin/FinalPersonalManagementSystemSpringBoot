package com.manager.information.service;

import com.manager.information.domain.Activity;
import com.manager.information.domain.ActivityType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public interface IActivityService {
    Activity createActivity(Activity activity);

    List<Activity> findAllActivity();

    Activity findById(Long id);
    Activity findByDate(Date date);
    Activity findBYName(ActivityType activityName);

    Activity updateActivity(Activity activity);

    void deleteById(Long id);
    public BigDecimal totalAmount();
}
