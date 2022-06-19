package com.manager.information.service;


import com.manager.information.domain.Activity;
import com.manager.information.domain.ActivityType;
import com.manager.information.repository.IActivityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ActivityServiceImp implements IActivityService {
    @Autowired
    private IActivityRep activityRep;

    @Override
    public Activity createActivity(Activity activity) {
        try {
            return activityRep.save(activity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Activity> findAllActivity() {
        return activityRep.findAll();
    }

    @Override
    public Activity findById(Long id) {
        return activityRep.getById(id);
    }

    @Override
    public Activity findByDate(Date date) {
        return activityRep.findByDate(date);
    }

    @Override
    public Activity findBYName(ActivityType activityName) {
        return activityRep.findByNameOfActivity(activityName);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        try {
            return activityRep.save(activity);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            activityRep.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public BigDecimal totalAmount() {
        return activityRep.totalAmount();
    }
}
