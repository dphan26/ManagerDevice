package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.TbUser;
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
	private List<TbUser> booker;

	public DisplaySearchModel() {

	}

	public DisplaySearchModel(List<Category> category, List<TbUser> booker) {
		this.category = category;
		this.booker = booker;
	}

}
