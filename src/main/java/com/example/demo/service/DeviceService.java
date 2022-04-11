package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.form.GroupBookingForm;
import com.example.demo.model.DeviceModel;



@Service
public interface DeviceService {
	List<DeviceModel> getAllListDevices() throws Exception;

	void updateBooking(GroupBookingForm bookingForm);
	

}
