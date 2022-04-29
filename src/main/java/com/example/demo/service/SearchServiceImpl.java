package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.Const;
import com.example.demo.entity.TbUser;
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
		List<TbUser> lstBooker = bookerRepository.findAll();

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
			//convert status id -> to status name
			String status = Const.LIST_STATUS_MAP.get(Integer.valueOf(dv.getStatus()));
			DeviceModel deviceModel = new DeviceModel(dv.getId(), dv.getDeviceName(), dv.getVersion(),
					dv.getBorrowedTime(), dv.getReturnedTime(), status, dv.getCategory(), dv.getBooker());
			lstDeviceModel.add(deviceModel);
		}

		return lstDeviceModel;
	}

}
