//package com.manager.information.implement;
//
//import com.manager.information.domain.*;
//import com.manager.information.repository.IActivityRep;
//
//import com.manager.information.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//@Component
//public class UserRegistrationImplement implements CommandLineRunner {
//    @Autowired
//    private UserRepository registrationRep;
//    @Autowired
//    private IActivityRep activityRep;
//    @Override
//    public void run(String... args) throws Exception {
////        UserRegistration userRegistration = new UserRegistration("celestin", TitleType.TOURIST,"celestin","celestin@gmail.com","rwanda");
////        UserRegistration kam = new UserRegistration("kamali",TitleType.DRIVER,"kamali","kama@gmail.com","kigali");
////        registrationRep.save(userRegistration);
////        registrationRep.save(kam);
////        Activity activity = new Activity(LocalDate.of(2022,05,07), ActivityType.RESTAURANT,20000,"I was town",
////        userRegistration);
////        Activity activity1 = new Activity(LocalDate.of(2022,05,06),ActivityType.GAZ,7000,"I was in Los Angels",
////                kam);
////        activityRep.save(activity);
////        activityRep.save(activity1);
//        Role role = new Role("admin");
//        Role role1 = new Role("user");
//        User userRegistration1 = new User("celestin", TitleType.TOURIST,"celestin","celestin1@gmail.com","rwanda",Arrays.asList(role));
//        User kam2 = new User("kamali",TitleType.DRIVER,"kamali","kama2@gmail.com","kigali",Arrays.asList(role1));
//        registrationRep.save(userRegistration1);
//        registrationRep.save(kam2);
//        Activity activity3 = new Activity(LocalDate.of(2022,05,07), ActivityType.RESTAURANT,20000,"I was town",
//                userRegistration1);
//        Activity activity4 = new Activity(LocalDate.of(2022,05,06),ActivityType.GAZ,7000,"I was in Los Angels",
//                kam2);
//        activityRep.save(activity3);
//        activityRep.save(activity4);
//
//        System.out.println("the number of user are : " +registrationRep.count());
//        System.out.println("the number of user are : " + activityRep.count());
//
//    }
//}
