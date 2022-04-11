package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Device;
import com.example.demo.form.GroupBookingForm;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DeviceModel;
import com.example.demo.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	DeviceRepository deviceRepository;

	@Override
	public List<DeviceModel> getAllListDevices() throws Exception {

		List<Device> listDevice = deviceRepository.getAllDevice();
		List<DeviceModel> listDeviceModel = new ArrayList<DeviceModel>();
		for (Device device : listDevice) {
			DeviceModel deviceModel = new DeviceModel(device.getId(), device.getDeviceName(), device.getVersion(),
					device.getBorrowedTime(), device.getReturnedTime(), device.getStatus(), device.getCategory(),
					device.getBooker());
			listDeviceModel.add(deviceModel);
		}
		return listDeviceModel;
	}

	/* @Override 
	public void updateInforBooking(String id, String version) {
		// TODO Auto-generated method stub
		deviceRepository.updateInforBooking(id, version);
	}*/
	
	@Override
	public void updateBooking(GroupBookingForm bookingForm) {
		// TODO Auto-generated method stub
		for(BookingModel bkModel : bookingForm.getBkDevices()) {
			String id = bkModel.getDevice_id();
			String status = "Requesting";
			Date borrowedTime = bkModel.getBorrowedTime();
			Date returnedTime = bkModel.getReturedTime();
			
			deviceRepository.updateInforBooking(id, status, borrowedTime, returnedTime);
		}
		
	}

}
