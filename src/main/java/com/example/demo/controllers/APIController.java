package com.example.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.RegisterForm;
import com.example.demo.model.RegisterModel;
import com.example.demo.service.DeviceService;

//note: type of Request body, like List, String, Object.. mapping with data send from post man
//https://www.baeldung.com/exception-handling-for-rest-with-spring?fbclid=IwAR0zFVnnplv1acQTRwsVbAw9NesxrEh1p1E6bBDwS-Zs7371XhOim1ukTwY
//https://hocspringboot.net/2021/09/30/validate-du-lieu-rest-api-trong-spring-boot/
@RestController
@Validated
@RequestMapping("api/v1")
public class APIController {
	private static final Logger LOGGER = LoggerFactory.getLogger(APIController.class);

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/devices")
	public ResponseEntity<String> addRegisterDevice(@RequestBody List<@Valid RegisterForm> lstRegisterForm) {
		// Convert from Form to Model object
		List<RegisterModel> regModel = lstRegisterForm.stream().map(regFrom -> mapper.map(regFrom, RegisterModel.class))
				.collect(Collectors.toList());
		deviceService.saveAllRegisterDevice(regModel);
		return ResponseEntity.ok("Devices is valid and save success");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<Set<String>> handleConstraintViolation(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		Set<String> messages = new HashSet<>(constraintViolations.size());
		messages.addAll(constraintViolations.stream()
				.map(constraintViolation -> String.format("%s value '%s' %s", constraintViolation.getPropertyPath(),
						constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
				.collect(Collectors.toList()));

		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);

	}

	// Check exist device ID:

	// List<String> deviceID = getAllDeviceID();
//			List<String> deviceID = deviceService.getAllDevicesID();
//			List<String> names = lstRegisterForm.stream()
//				              .map(RegisterForm::getDeviceId)
//				              .collect(Collectors.toList());
//			boolean var = names.stream().anyMatch(element -> deviceID.contains(element));
//			//if(!Collections.disjoint(deviceID, lstRegisterForm.ge);)
//			// Convert from Form to Model object
//			List<RegisterModel> regModel = lstRegisterForm.stream()
//					.map(regFrom -> mapper.map(regFrom, RegisterModel.class)).collect(Collectors.toList());
//			deviceService.saveAllRegisterDevice(regModel);
////	    	 if (result.hasErrors()) {
////	  	       
////	    		 return new ResponseEntity<>(asset, HttpStatus.EXPECTATION_FAILED);
////			    }		      
//			return new ResponseEntity<>(regModel, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
