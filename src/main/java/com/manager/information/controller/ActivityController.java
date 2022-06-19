//package com.manager.information.controller;
//
//import com.manager.information.domain.Activity;
//import com.manager.information.domain.ActivityType;
//import com.manager.information.service.IActivityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class ActivityController {
//    @Autowired
//    private IActivityService activityService;
//
//    @GetMapping(value = "/helloWord",produces = "application/json")
//    public ResponseEntity<?> findAll(){
//        Activity activity = new Activity();
//        activity.setDate(null);
//        activity.setNameOfActivity(ActivityType.RESTAURANT);
//        activity.setAmount(25864);
//        activity.setComment("I appreciated");
//        return new ResponseEntity<>(activity,HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/findAll",produces = "application/json")
//    public ResponseEntity<?> findAllActivities(){
//        return new ResponseEntity<>(activityService.findAllActivity(),HttpStatus.OK);
//
//    }
//    @PostMapping(name = "/createActivity",consumes = "application/json",produces = "application/json")
//    public ResponseEntity<?> activityCreation(@RequestBody Activity activity){
//        return new ResponseEntity<>(activityService.createActivity(activity),HttpStatus.OK);
//
//
//    }
//    @PostMapping(value = "/deleteActivity",produces = "application/json")
//    public ResponseEntity<?> deleteActivity(@RequestParam("act_id") Long id){
//        activityService.deleteById(id);
//        return new ResponseEntity<>("deleted was Successful",HttpStatus.OK);
//
//
//    }
////    @PostMapping(value = "/deleteStudent",produces = "application/json")
////    public ResponseEntity<?> deleteStudent(@RequestParam("student_id")String studentId){
////        studentService.removeStudent(Integer.parseInt(studentId));
////        return new ResponseEntity<>("deleted Sucessfull",HttpStatus.OK);
//
//}
