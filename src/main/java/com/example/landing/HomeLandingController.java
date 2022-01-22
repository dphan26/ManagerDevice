package com.example.landing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeLandingController {
	 @RequestMapping({"/homeLanding"})  
	  public String home(Model model) {  
//		 List<User> users =  userService.getAllUser();
//	   model.addAttribute("users", users);  
	    return "home_landing";  
	  }  

}
