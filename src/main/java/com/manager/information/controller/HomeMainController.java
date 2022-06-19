package com.manager.information.controller;

import com.manager.information.domain.Activity;
import com.manager.information.domain.ActivityType;
import com.manager.information.domain.TitleType;
import com.manager.information.domain.User;
import com.manager.information.repository.UserRepository;
import com.manager.information.service.IActivityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeMainController {
    @Autowired
    private UserRepository userRegistrationService;
    @Autowired
    private IActivityService activityService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/userMainDashboard")
    public String getUserDashboard() {
        return "admin/adminPage";
    }



    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userRegistrationService.findAll());
        model.addAttribute("ETitle", TitleType.values());
        model.addAttribute("activities", activityService.findAllActivity());
        model.addAttribute("EActivityType", ActivityType.values());
        model.addAttribute("usersAccount", userRegistrationService.findAll().size());
        model.addAttribute("activityAccount", activityService.findAllActivity().size());
        List<User> userRegistrations = userRegistrationService.findAll();
        List<Activity> activities = activityService.findAllActivity();


        List<Enum> activityNames = new ArrayList<>();
        for (Activity act : activities) {
            activityNames.add(act.getNameOfActivity());
        }
        List<Integer> numberOfUserRegistered = new ArrayList<>();
        for (User userRegistration : userRegistrations) {
            numberOfUserRegistered.add(userRegistration.getActivities().size());
        }
        model.addAttribute("userRegistered",numberOfUserRegistered);
        model.addAttribute("activityName",activityNames);
        return "index";
    }
    @GetMapping("/test")
    public String EditProfile(Authentication auth, Model model){
        String findUserName = auth.getName();
        User userRegistration = userRegistrationService.findByEmail(findUserName);
        if (userRegistration == null){
            logger.error("This UserName couldn't not found please check out userName perfectly!!");
        }
        model.addAttribute("userLogged",userRegistration);
        return "EditProfile";


    }

}
