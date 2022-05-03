package com.example.demo.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.form.ConditionSearchForm;
import com.example.demo.model.DeviceModel;
import com.example.demo.model.DisplaySearchModel;




@Service
public interface SearchService {
	DisplaySearchModel getDataFormSearch() throws Exception;
	Page<DeviceModel>  getListDeviceByConditionSearch(ConditionSearchForm conditionSearchForm);

}
