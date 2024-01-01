package com.example.demo.model;

import java.util.Set;

import lombok.Data;

@Data
public class CategoryModel {

	private Integer id;

	private String categoryName;

	private Set<DeviceModel> device;

}
