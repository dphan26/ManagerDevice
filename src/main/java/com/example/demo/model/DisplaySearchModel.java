package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.TblUser;
import com.example.demo.entity.Category;

import lombok.Data;

/**
 * 
 * Object transfer data between Controller and Entity
 * 
 * @author DoPT
 *
 *
 */
@Data
public class DisplaySearchModel {
	// Display list category
	private List<Category> category;

	// Display list booker
	private List<TblUser> user;

	public DisplaySearchModel() {

	}

	public DisplaySearchModel(List<Category> category, List<TblUser> user) {
		this.category = category;
		this.user = user;
	}

}
