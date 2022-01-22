package com.example.landing;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Component
public class HomeLandingComponent {
	 @RequestMapping({"/homeLandingComponent"})  
	  public String home(Model model) {  
//		 List<User> users =  userService.getAllUser();
//	   model.addAttribute("users", users);  
	    return "home";  
	  }  

}
