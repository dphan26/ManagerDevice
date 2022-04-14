package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.RegisterForm;
import com.example.demo.model.RegisterModel;
import com.example.demo.service.DeviceService;

//note: type of Request body, like List, String, Object.. mapping with data send from post man

@RestController
public class APIController {
	private static final Logger LOGGER = LoggerFactory.getLogger(APIController.class);

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/api/addRegisterDevice")
	public ResponseEntity<List<RegisterModel>> addRegisterDevice(@RequestBody List<RegisterForm> lstRegisterForm) {

		System.out.println("dopt" + lstRegisterForm);
		try {
			// Convert from Form to Model object
			List<RegisterModel> regModel = lstRegisterForm.stream()
					.map(regFrom -> mapper.map(regFrom, RegisterModel.class)).collect(Collectors.toList());
			deviceService.saveAllRegisterDevice(regModel);
//	    	 if (result.hasErrors()) {
//	  	       
//	    		 return new ResponseEntity<>(asset, HttpStatus.EXPECTATION_FAILED);
//			    }
//			homePageService.addAsets(asset);			      
			return new ResponseEntity<>(regModel, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
