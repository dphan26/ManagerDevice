package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Device;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DeviceModel;
import com.example.demo.repository.DeviceRepository;

@Service
public class BookingServiceImpl implements BookingService  {
	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<BookingModel> getListInforBooking(List<String> ids) throws Exception {
		
		List<Device> listInforBooking = deviceRepository.getListInforBooking(ids);
		List<BookingModel> listBookingModel = new ArrayList<BookingModel>();
		for (Device device : listInforBooking) {
			BookingModel bookingModel = new BookingModel(device.getId(), device.getDeviceName(), device.getVersion(),
					device.getBorrowedTime(), device.getReturnedTime(), device.getRemark());
			listBookingModel.add(bookingModel);
		}	
		return listBookingModel;
	}

}
