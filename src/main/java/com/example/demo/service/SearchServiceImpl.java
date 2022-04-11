package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booker;
import com.example.demo.entity.Category;
import com.example.demo.entity.Device;
import com.example.demo.form.ConditionSearchForm;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.DisplaySearchModel;
import com.example.demo.repository.BookerRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.SearchRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	BookerRepository bookerRepository;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	SearchRepository searchRepository;

	@Override
	public DisplaySearchModel getDataFormSearch() throws Exception {

		List<Category> lstCtgry = categoryRepository.findAll();
		List<Booker> lstBooker = bookerRepository.findAll();

		DisplaySearchModel displaySearchModel = new DisplaySearchModel();
		// Convert from result respository(entity) to object form
		displaySearchModel.setCategory(lstCtgry);
		displaySearchModel.setBooker(lstBooker);
		return displaySearchModel;
	}

	@Override
	public List<DeviceModel> getListDeviceByConditionSearch(ConditionSearchForm conditionSearchForm) {
		// TODO Auto-generated method stub

		List<Device> lstDevices = searchRepository.findBooksByAuthorNameAndTitle(conditionSearchForm);

		List<DeviceModel> lstDeviceModel = new ArrayList<DeviceModel>();
		for (Device dv : lstDevices) {
			DeviceModel deviceModel = new DeviceModel(dv.getId(), dv.getDeviceName(), dv.getVersion(),
					dv.getBorrowedTime(), dv.getReturnedTime(), dv.getStatus(), dv.getCategory(), dv.getBooker());
			lstDeviceModel.add(deviceModel);
		}

		return lstDeviceModel;
	}

}
