package com.example.demo.subcontroller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SubController {
	 @RequestMapping({"/subhome"})  
	  public String home(Model model) {  
//		 List<User> users =  userService.getAllUser();
//	   model.addAttribute("users", users);  
	    return "subhome";  
	  }

	public SubController() {
		System.out.println("constructor : SubController khi tạo ra bean để vào ApplicationContext ");
		// TODO Auto-generated constructor stub
	}  
	 

}
