package com.example.demo.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Device;
import com.example.demo.form.ListBookingForm;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.RegisterModel;



@Service
public interface DeviceService {
	List<DeviceModel> getAllListDevices() throws Exception;
	
	void updateBooking(ListBookingForm bookingForm);
	void saveAllRegisterDevice(List<RegisterModel> regModel);
	
	Page<DeviceModel> findPage(int pageNumber);
	

}
