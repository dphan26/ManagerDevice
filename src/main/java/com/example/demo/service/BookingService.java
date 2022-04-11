package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.BookingModel;
import com.example.demo.model.DeviceModel;



@Service
public interface BookingService {
	List<BookingModel> getListInforBooking(List<String> ids) throws Exception;

}
