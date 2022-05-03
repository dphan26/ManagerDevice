package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.constant.Const;
import com.example.demo.entity.Category;
import com.example.demo.entity.Device;
import com.example.demo.entity.TblUser;
import com.example.demo.form.ConditionSearchForm;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.DisplaySearchModel;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.SearchRepository;
import com.example.demo.repository.UserRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	SearchRepository searchRepository;

	@Override
	public DisplaySearchModel getDataFormSearch() throws Exception {

		List<Category> lstCtgry = categoryRepository.findAll();
		List<TblUser> lstUsers = userRepository.findAll();

		DisplaySearchModel displaySearchModel = new DisplaySearchModel();
		// Convert from result respository(entity) to object form
		displaySearchModel.setCategory(lstCtgry);
		displaySearchModel.setUser(lstUsers);
		return displaySearchModel;
	}

	@Override
	public Page<DeviceModel> getListDeviceByConditionSearch(ConditionSearchForm conditionSearchForm) {
		// TODO Auto-generated method stub

		Page<Device> lstDevices = searchRepository.findDeviceByConditionSearch(conditionSearchForm);
		Page<DeviceModel> listDeviceModel = lstDevices.map(obj -> new DeviceModel(obj));

		return listDeviceModel;
	}

}
