package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model_form.AddingAssetForm;
import com.example.demo.model_form.SearchSiteForm;
import com.example.demo.repository.UserBRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.datamodel.Asset;
import com.example.demo.repository.datamodel.User;
import com.example.demo.repository.datamodel.UserB;
import com.example.demo.repository.service.HomePageService;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	/*
	 * ======================================================================
	 * ====================== Configuration beans ===========================
	 * ======================================================================
	 */
	@Autowired
	private HomePageService homePageService;

//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	UserBRepository userBRepository;

//	@ModelAttribute
//	public void modelData(Model m) {
//		m.addAttribute("dopt", "valueNameDoPT");
//	}

	@RequestMapping({ "/", "/home" })
	public String home(@ModelAttribute Asset assetForm, @ModelAttribute SearchSiteForm searchSiteForm, Model model)
			throws Exception {
		model.addAttribute("searchSiteForm", new SearchSiteForm());
		model.addAttribute("assetFormAction", new AddingAssetForm());
		// Display list users information from Assets Table
		List<Asset> assets = homePageService.getAllListAsets();

//		 https://stackoverflow.com/questions/7309259/get-list-of-attributes-of-an-object-in-an-list
		List<String> lstSite = assets.stream().distinct().map(Asset::getSite).collect(Collectors.toList());
		Map<String, String> mapSite = lstSite.stream().distinct()
				.collect(Collectors.toMap(Function.identity(), s -> s));
		mapSite.forEach((x, y) -> System.out.println("Key: " + x + ", value: " + y));
		model.addAttribute("mapSite", mapSite);
		model.addAttribute("assets", assets);
		model.addAttribute("assetsEdit", new Asset());
		model.addAttribute("assetForm", new Asset());
		System.out.println("@Controller HomeController");
		return "home";
	}

	@RequestMapping("/addAssetForm")
	public String addAssetForm(Model model) throws Exception {
		model.addAttribute("addingAssetForm", new AddingAssetForm());
		return "add_user";
	}

	@PostMapping("/saveAsset")
	public String addAsset(@ModelAttribute AddingAssetForm addingAssetForm, Model model) throws Exception {
		model.addAttribute("searchSiteForm", new SearchSiteForm());
		Asset asset = new Asset();
		asset.setId_asset(addingAssetForm.getId_asset());
		;
		asset.setName(addingAssetForm.getName());
		asset.setAccount(addingAssetForm.getAccount());
		asset.setIp(addingAssetForm.getIp());
		asset.setDepartment(addingAssetForm.getDepartment());
		asset.setReceived_date(addingAssetForm.getReceived_date());
		asset.setStatus(addingAssetForm.getStatus());
		asset.setSite(addingAssetForm.getSite());
		homePageService.addAsets(asset);
		// Display list users information from Assets Table
		List<Asset> assets = homePageService.getAllListAsets();
		model.addAttribute("assets", assets);
		model.addAttribute("assetForm", new Asset());
//		 https://stackoverflow.com/questions/7309259/get-list-of-attributes-of-an-object-in-an-list
		List<String> lstSite = assets.stream().distinct().map(Asset::getSite).collect(Collectors.toList());
		Map<String, String> mapSite = lstSite.stream().distinct()
				.collect(Collectors.toMap(Function.identity(), s -> s));
		model.addAttribute("mapSite", mapSite);
		return "home";
	}


	@PostMapping("/searchAccount")
	public String searchAccount(@ModelAttribute AddingAssetForm addingAssetForm, Model model) throws Exception {
		model.addAttribute("searchSiteForm", new SearchSiteForm());
		model.addAttribute("assetForm", new Asset());
		// Display list users information from Assets Table base on search account
		List<Asset> assetsAccountSearch = homePageService.findByAccount(addingAssetForm.getAccount());
		model.addAttribute("assets", assetsAccountSearch);
//		 model.addAttribute("assetsEdit", new Asset());

//		 List<Asset> assets =  homePageService.getAllListAsets();
//		 model.addAttribute("accountsearch", addingAssetForm.getAccount());  
		// Display list users information from Assets Table
		List<Asset> assets = homePageService.getAllListAsets();
//		 model.addAttribute("assets", assets);  
		model.addAttribute("assetForm", new Asset());
//		 https://stackoverflow.com/questions/7309259/get-list-of-attributes-of-an-object-in-an-list
		List<String> lstSite = assets.stream().distinct().map(Asset::getSite).collect(Collectors.toList());
		Map<String, String> mapSite = lstSite.stream().distinct()
				.collect(Collectors.toMap(Function.identity(), s -> s));
		model.addAttribute("mapSite", mapSite);
		return "home";
	}

	/* Display list asset for site search */
	@PostMapping("/searchSiteForm")
	public String searchFormSite(@ModelAttribute SearchSiteForm searchSiteForm, Model model) throws Exception {
		/* Create form mapping for site search */
		model.addAttribute("searchSiteForm", new SearchSiteForm());
		List<Asset> assets = homePageService.getAllListAsets();
		List<String> lstSite = assets.stream().distinct().map(Asset::getSite).collect(Collectors.toList());
		Map<String, String> mapSite = lstSite.stream().distinct()
				.collect(Collectors.toMap(Function.identity(), s -> s));
		mapSite.forEach((x, y) -> System.out.println("Key: " + x + ", value: " + y));
		model.addAttribute("mapSite", mapSite);
		/* Create form mapping for account search */
		model.addAttribute("assetForm", new Asset());
		/* Display list assets for site search */
		if (!searchSiteForm.getId_site().equals("")) {
			List<Asset> assetsSiteSearch = homePageService.findBySite(searchSiteForm.getId_site());
			model.addAttribute("assets", assetsSiteSearch);
		} else {
			model.addAttribute("assets", assets);
		}

		/* Display site on select box start */

		/* Display site on select box start */
		return "home";
	}

	@PostMapping("/getSiteList")
	public String getSiteList(@ModelAttribute AddingAssetForm addingAssetForm, Model model) throws Exception {

		// Display list users information from Assets Table base on search account
		List<Asset> assets = homePageService.findByAccount(addingAssetForm.getAccount());
		model.addAttribute("assets", assets);
		model.addAttribute("assetsEdit", new Asset());
		model.addAttribute("assetForm", new Asset());
//		 List<Asset> assets =  homePageService.getAllListAsets();
		model.addAttribute("accountsearch", addingAssetForm.getAccount());
		// Display list users information from Assets Table

		model.addAttribute("assets", assets);
		return "home";
	}

//	@RequestMapping(value="/deleteAsset", method=RequestMethod.POST)
	@GetMapping("/deleteAsset")
	public String deleteAsset(@PathVariable("id") String id) {
		System.out.println(id);
		homePageService.deleteAsset(id);
		return "redirect:/home";
	}
	
//	   @PostMapping("/deleteBuyer/{id}")
//	    public String deleteBuyer(@PathVariable Long id){
//	        buyerService.deleteBuyer(id);
//	        return "redirect:/";
//	    }
	
	@RequestMapping(value="/editAsset", method=RequestMethod.POST)
	public String editAsset() {
		return "edit_asset";
	}



//	@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=cancel")
//public ModelAndView cancel() {}

	@GetMapping("/customer/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
//	     model.addAttribute("customer", customerService.findById(id));
		return "/edit";
	}

	// https://stackoverflow.com/questions/41314724/search-via-text-field-and-button-in-spring-mvc-crudrepository-thymeleaf

}
