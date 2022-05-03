package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constant.Const;
import com.example.demo.entity.Category;
import com.example.demo.entity.TblUser;
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
	
	@RequestMapping(value = {"/", "/devices" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String home(@ModelAttribute ConditionSearchForm conditionSearchForm, Model model,
			 Authentication authentication) throws Exception {
			return getOnePage(conditionSearchForm, model, authentication, 1);	
	}
		
	
	@RequestMapping(value = {"/devices/page/{pageNumber}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String getOnePage(@ModelAttribute ConditionSearchForm conditionSearchForm, 
			Model model, Authentication authentication,
			@PathVariable("pageNumber") int currentPage) throws Exception {
		
		MyUserDetails inforUser = (MyUserDetails) authentication.getPrincipal();
		
		Page<DeviceModel> page  ;
		int totalPages = 0;
		long totalItems = 0;
		List<DeviceModel> lstdevices = null;
		
//		 If have condition search
		if (StringUtils.isNotBlank(conditionSearchForm.getCategoryId())
				|| StringUtils.isNotBlank(conditionSearchForm.getVersion())
				|| StringUtils.isNotBlank(conditionSearchForm.getDeviceIdOrName())
				|| StringUtils.isNotBlank(conditionSearchForm.getSite())
				|| StringUtils.isNotBlank(conditionSearchForm.getStatus())
				|| StringUtils.isNotBlank(conditionSearchForm.getBookerId())
				|| (conditionSearchForm.getBorrowedTime()!=null)
				|| (conditionSearchForm.getReturnedTime()!=null)) {
			page = searchService.getListDeviceByConditionSearch(conditionSearchForm);
		} else {
//			Don't have condition search, get all list devices
			 page = deviceService.findPage(currentPage);		
		}
		totalPages = page.getTotalPages();
		totalItems = page.getTotalElements();
		lstdevices = page.getContent();

		// Get data for form Search
		DisplaySearchModel displaySearchModel = searchService.getDataFormSearch();
		List<Category> lstCategory = displaySearchModel.getCategory();
		List<TblUser> lstUser = displaySearchModel.getUser();

		// Convert from model to object form
		List<DeviceForm> lstDeviceForm = lstdevices.stream().map(user -> mapper.map(user, DeviceForm.class))
				.collect(Collectors.toList());
		// Add data to view for paging
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		// Add data to view formName='listDevice'
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("devices", lstDeviceForm);	
		// Add data to view formName='search'
		model.addAttribute("lstCategory", lstCategory);
		model.addAttribute("lstUser", lstUser);
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
		return "redirect:/";
	}

	@PostMapping({"/saveBookingDevice"})
	public String saveBookingDevice(@Valid @ModelAttribute ListBookingForm listBookingForm,
			BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {	
			model.addAttribute("listBookingForm", listBookingForm);
			return "booking_device";
		}
		deviceService.updateBooking(listBookingForm);
		return "redirect:/";
	}

	@PostMapping({ "/back" })
	public String back() throws Exception {
		return "redirect:/";
	}
	
	

}
