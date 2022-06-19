package com.manager.information.controller;

import com.manager.information.domain.TitleType;
import com.manager.information.domain.User;

import com.manager.information.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserRegistrationControllerUI {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("about")
    public String getAbout(){
        return "about";
    }
    @GetMapping("ShowTestimonial")
    public String viewTestimonial(){
        return "testimonial";
    }
    @GetMapping("starter")
    public String viewStarter(){
        return "starter";
    }
    @GetMapping("book")
    public String viewBook(){
        return "book";
    }

    @GetMapping("/viewUserRegister")
    public String getUserRegistration(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("ETitle", TitleType.values());
       return "user/DisplayAllUserRegistration";


    }

    @RequestMapping("/userRegistration")
    // /userRegistration: API : application programming  Interface / API should test in postman and swagger
    public String createUserRegistration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("ETitleType", TitleType.values());
       // return "user/newUserRegistration";
        return "registration";

    }

    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_successfully";

    }
    @GetMapping("/showFormForUpdateUser/{id}")
    public ModelAndView DesplayActivity(@PathVariable(value = "id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("user/updateUser");
        model.addAttribute("ETitle", TitleType.values());
        User existingUser = userRepository.getById(id);
        modelAndView.addObject("user",existingUser );
        return modelAndView;
    }
//    @GetMapping("/deleteActivity/{id}")
//    public String removeStudent(@PathVariable(value = "id") Long actId) {
//        activityService.deleteById(actId);
//        return "redirect:/viewActivity";
//}
    @GetMapping("/deleteUser/{id}")
    public String removeStudent(@PathVariable(value = "id") Long id){
        userRepository.deleteById(id);
        return "redirect:/viewUserRegister";
    }

}







