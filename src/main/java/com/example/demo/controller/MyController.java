package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@GetMapping("/greeting")
	public String getGreeting(@RequestParam(value = "language", defaultValue = "fr") String lang,
			String name, Model model) {
		Locale locale = new Locale(lang);
		System.out.println(messageSource.getMessage("greeting", null, locale));
		name = messageSource.getMessage("greeting", null, locale);
		model.addAttribute("name", name);
		return "greeting";

	}
	@GetMapping("/savedata")
	public String savedata(@RequestParam(value = "language", defaultValue = "fr") String lang,
			String name, Model model) {
		
		return "savelocalstorage";
		
	}
}
