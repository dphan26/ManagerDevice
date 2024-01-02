package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.ConditionSearchForm;

@Controller
public class MultiLanguageController {
//https://shareprogramming.net/huong-dan-su-dung-requestparam-trong-spring-boot/
	@PostMapping("/multi-language")
	public String changeLanguage(
			RedirectAttributes redirectAttributes, @ModelAttribute ConditionSearchForm conditionSearchForm) {
		//LocaleResolver localeResolver = (LocaleResolver) RequestContextUtils.getLocaleResolver(request);
	
		//keep all value of form all current screen
		 // Process the form data
		String a = conditionSearchForm.getSite();
		String b = conditionSearchForm.getStatus();
		List lstData = new ArrayList<String>();
		lstData.add(a);
		lstData.add(b);
		redirectAttributes.addFlashAttribute("data", lstData);
		
 		 return "redirect:" + "/multi-language";
	}
	
	@GetMapping("/multi-language")
	public String changeLanguage(@RequestParam("language") String language,
			HttpServletRequest request , HttpServletResponse response, HttpSession session,
			Model model,
			 @ModelAttribute("data") String data) {
		//LocaleResolver localeResolver = (LocaleResolver) RequestContextUtils.getLocaleResolver(request);
	
		//keep all value of form all current screen
		session.setAttribute("myAttribute", "myValue");
		
		model.addAttribute("data", data);		
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
}
