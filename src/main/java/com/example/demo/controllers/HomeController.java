package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constant.Const;
import com.example.demo.entity.Booker;
import com.example.demo.entity.Category;
import com.example.demo.form.BookingForm;
import com.example.demo.form.ConditionSearchForm;
import com.example.demo.form.DeviceForm;
import com.example.demo.form.GroupBookingForm;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.DisplaySearchModel;
import com.example.demo.service.BookingService;
import com.example.demo.service.DeviceService;
import com.example.demo.service.SearchService;

//https://techmaster.vn/posts/36183/spring-boot-12-spring-jpa-method-atquery
//https://www.baeldung.com/jpa-return-multiple-entities
//https://www.baeldung.com/java-modelmapper-lists

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
	

	@RequestMapping({ "/", "/home" })
	public String home(@ModelAttribute ConditionSearchForm conditionSearchForm, Model model) throws Exception {
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
		List<Booker> lstBooker = displaySearchModel.getBooker();

		// Convert from model to object form
		List<DeviceForm> lstDf = lstDeviceModel.stream().map(user -> mapper.map(user, DeviceForm.class))
				.collect(Collectors.toList());

		// Add data to view
		model.addAttribute("lstCategory", lstCategory);
		model.addAttribute("lstBooker", lstBooker);
		model.addAttribute("devices", lstDf);
		model.addAttribute("sites", Const.LIST_SITE_MAP);
		model.addAttribute("statusMap", Const.LIST_STATUS_MAP);
		model.addAttribute("conditionSearchForm", conditionSearchForm);

		return "home_device";
	}
	
	@RequestMapping({ "/bookingDevice" })
	public String bookingDevice(@RequestParam(required = false) List<String> deviceId, Model model) throws Exception {
		if (deviceId != null) {
			List<BookingModel> lstInforBooking = bookingService.getListInforBooking(deviceId);
			// Convert from model to object form
			List<BookingForm> lstBkForm = lstInforBooking.stream().map(user -> mapper.map(user, BookingForm.class))
					.collect(Collectors.toList());
			GroupBookingForm bookingForm = new GroupBookingForm();
			bookingForm.setBkDevices(lstBkForm);
			model.addAttribute("bookingForm", bookingForm);
			return "booking_device";
		}
		return "redirect:/home";
	}

	@PostMapping({ "/saveBookingDevice" })
	public String saveBookingDevice(@ModelAttribute GroupBookingForm bookingForm, Model model) throws Exception {
		deviceService.updateBooking(bookingForm);
		return "redirect:/home";
	}

	@PostMapping({ "/back" })
	public String back() throws Exception {
		return "redirect:/home";
	}

}
