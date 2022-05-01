package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constant.Const;
import com.example.demo.entity.TblUser;
import com.example.demo.entity.Category;
import com.example.demo.form.BookingForm;
import com.example.demo.form.ConditionSearchForm;
import com.example.demo.form.DeviceForm;
import com.example.demo.form.ListBookingForm;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.DisplaySearchModel;
import com.example.demo.service.BookingService;
import com.example.demo.service.DeviceService;
import com.example.demo.service.MyUserDetails;
import com.example.demo.service.SearchService;
//https://www.codejava.net/frameworks/spring-boot/spring-boot-security-authentication-with-jpa-hibernate-and-mysql
//https://techmaster.vn/posts/36183/spring-boot-12-spring-jpa-method-atquery
//https://www.baeldung.com/jpa-return-multiple-entities
//https://www.baeldung.com/java-modelmapper-lists
//https://levunguyen.com/laptrinhspring/2020/04/21/su-dung-spring-security-trong-spring/#:~:text=3.-,H%C6%B0%E1%BB%9Bng%20d%E1%BA%ABn%20x%C3%A2y%20d%E1%BB%B1ng%20%E1%BB%A9ng%20d%E1%BB%A5ng%20Spring%20Security,file%20configure%20c%E1%BB%A7a%20spring%20security%20.
@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = {"/", "/home" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String home(@ModelAttribute ConditionSearchForm conditionSearchForm, 
			Model model, Authentication authentication) throws Exception {
		MyUserDetails inforUser = (MyUserDetails) authentication.getPrincipal();
		List<DeviceModel> lstDeviceModel = null;
		// If have condition search
		if (StringUtils.isNotBlank(conditionSearchForm.getCategoryId())
				|| StringUtils.isNotBlank(conditionSearchForm.getVersion())
				|| StringUtils.isNotBlank(conditionSearchForm.getDeviceIdOrName())
				|| StringUtils.isNotBlank(conditionSearchForm.getSite())
				|| StringUtils.isNotBlank(conditionSearchForm.getStatus())
				|| StringUtils.isNotBlank(conditionSearchForm.getBookerId())
				|| (conditionSearchForm.getBorrowedTime()!=null)
				|| (conditionSearchForm.getReturnedTime()!=null)) {
			lstDeviceModel = searchService.getListDeviceByConditionSearch(conditionSearchForm);
		} else {
			//Don't have condition search, get all list devices
			lstDeviceModel = deviceService.getAllListDevices();
		}
		// Get data for form Search
		DisplaySearchModel displaySearchModel = searchService.getDataFormSearch();
		List<Category> lstCategory = displaySearchModel.getCategory();
		List<TblUser> lstUser = displaySearchModel.getUser();

		// Convert from model to object form
		List<DeviceForm> lstDf = lstDeviceModel.stream().map(user -> mapper.map(user, DeviceForm.class))
				.collect(Collectors.toList());

		// Add data to view
		model.addAttribute("lstCategory", lstCategory);
		model.addAttribute("lstUser", lstUser);
		model.addAttribute("devices", lstDf);
		model.addAttribute("sites", Const.LIST_SITE_MAP);
		model.addAttribute("statusMap", Const.LIST_STATUS_MAP);
		model.addAttribute("conditionSearchForm", conditionSearchForm);
		model.addAttribute("inforUser", inforUser);

		return "home_device";
	}
	
	@GetMapping(value = {"/bookingDevice"})
	public String bookingDevice(@RequestParam(required = false) List<String> deviceId, Model model) throws Exception {
		if (deviceId != null) {
			List<BookingModel> lstInforBooking = bookingService.getListInforBooking(deviceId);
			// Convert from model to object form
			List<BookingForm> listBooking = lstInforBooking.stream().map(user -> mapper.map(user, BookingForm.class))
					.collect(Collectors.toList());
			ListBookingForm listBookingForm = new ListBookingForm();
			listBookingForm.setLstBooking(listBooking);
			model.addAttribute("listBookingForm", listBookingForm);
			return "booking_device";
		}
		return "redirect:/home";
	}

	@PostMapping({"/saveBookingDevice"})
	public String saveBookingDevice(@Valid @ModelAttribute ListBookingForm listBookingForm,
			BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {	
			model.addAttribute("listBookingForm", listBookingForm);
			return "booking_device";
		}
		deviceService.updateBooking(listBookingForm);
		return "redirect:/home";
	}

	@PostMapping({ "/back" })
	public String back() throws Exception {
		return "redirect:/home";
	}
	
	

}
