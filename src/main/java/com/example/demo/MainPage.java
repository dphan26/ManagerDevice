package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainPage {
	
	@Autowired
	HomeController homeController;

	public MainPage(HomeController homeController) {
		//super();
		System.out.println(" constructor: HomeController have a paramter");
		this.homeController = homeController;
	}
	
	public MainPage() {
		//super();
		System.out.println(" constructor:HomeController no have a paramter");
	}
	
	
	 

}
