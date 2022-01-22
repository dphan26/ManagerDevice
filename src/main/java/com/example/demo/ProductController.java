package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




public class ProductController {
	 @RequestMapping({"/product","/review"})  
	  public String home(Model model) {  
//		 List<User> users =  userService.getAllUser();
//	   model.addAttribute("users", users);  
	    return "home";  
	  }  

}
