package com.example.demo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Alien;



@Controller
public class HomeController {
	
	@ModelAttribute
	public void modelData(Model m) {
		m.addAttribute("dopt", "valueNameDoPT");
	}
	
	
	 @RequestMapping({"/", "/home"})  
	  public String home() {  
//		 List<User> users =  userService.getAllUser();
//	   model.addAttribute("users", users);  
		 System.out.println("@Controller HomeController");
	    return "index1";  
	  }

	public HomeController() {
		System.out.println("constructor HomeController");
		//super();
		// TODO Auto-generated constructor stub
	}  
	

	 @RequestMapping({"/add"})  
	  public String add(@RequestParam("num1") int i, @RequestParam("num2") int j, ModelMap m) {  
		 	int num3 = i + j;
			m.addAttribute("num3", num3);
			
	    return "result";  
	  }
	 
	 @RequestMapping({"/addAlien"})  
	  public String addAlien(@ModelAttribute Alien alien) {  
		
	    return "result";  
	  }
	 

}
