package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.constant.Const;
import com.example.demo.entity.Category;
import com.example.demo.entity.Device;
import com.example.demo.form.BookingForm;
import com.example.demo.form.ListBookingForm;
import com.example.demo.model.BookingModel;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.RegisterModel;
import com.example.demo.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<DeviceModel> getAllListDevices() throws Exception {

		List<Device> listDevice = deviceRepository.findAll();
		List<DeviceModel> listDeviceModel = new ArrayList<DeviceModel>();
		// convert from entity to model
		for (Device device : listDevice) {
			// convert status id -> to status name
			String status = Const.LIST_STATUS_MAP.get(Integer.valueOf(device.getStatus()));
			DeviceModel deviceModel = new DeviceModel(device.getId(), device.getDeviceName(), device.getVersion(),
					device.getBorrowedTime(), device.getReturnedTime(), status, device.getCategory(), device.getUser());
			listDeviceModel.add(deviceModel);
		}
		return listDeviceModel;
	}

	@Override
	public void updateBooking(ListBookingForm bookingForm) {
		List<Device> lstDevice = new ArrayList<Device>();
		// Convert from model to object form
		List<BookingModel> lstBkModel = bookingForm.getLstBooking().stream()
				.map(user -> mapper.map(user, BookingModel.class)).collect(Collectors.toList());
		for (BookingModel bkModel : lstBkModel) {
			String id = bkModel.getDevice_id();
			String status = "Requesting";
			Date borrowedTime = bkModel.getBorrowedTime();
			Date returnedTime = bkModel.getReturedTime();
			String remark = bkModel.getRemark();
			// Device dv = new Device(id, borrowedTime, returnedTime, status, remark);
			// lstDevice.add(dv);
			deviceRepository.updateInforBooking(id, status, borrowedTime, returnedTime, remark);
		}
		// deviceRepository.saveAll(lstDevice); -> save/update all column of table

	}

	@Override
	public void saveAllRegisterDevice(List<RegisterModel> lstregModel) {
		List<Device> lstDevice = new ArrayList<Device>();
		String status = Const.STATUS_REGISTER;
		for (RegisterModel regMd : lstregModel) {
			Category category = new Category();
			category.setId(Integer.valueOf(regMd.getCategoryId()));

			Device device = new Device(regMd.getDeviceId(), regMd.getDeviceName(), regMd.getVersion(), null, null,
					status, regMd.getSite(), null, category, null);
			lstDevice.add(device);
			// Device device = new Device(id, deviceName, version, borrowedTime,
			// returnedTime, status, site, remark, category, booker)
		}

		deviceRepository.saveAll(lstDevice);

	}

	@Override
	public Page<DeviceModel> findPage(int pageNumber) {

		Pageable pageable = PageRequest.of(pageNumber - 1, 5);

		Page<Device> listDevice = deviceRepository.findAll(pageable);

		Page<DeviceModel> listDeviceModel = listDevice.map(obj -> new DeviceModel(obj));

		return listDeviceModel;
	}

}
