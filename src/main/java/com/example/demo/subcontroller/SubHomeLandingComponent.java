package com.example.demo.subcontroller;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Component
public class SubHomeLandingComponent {
	 @RequestMapping({"/subHomeLandingComponent"})  
	  public String home(Model model) {  
//		 List<User> users =  userService.getAllUser();
//	   model.addAttribute("users", users);  
	    return "home";  
	  }  

}
