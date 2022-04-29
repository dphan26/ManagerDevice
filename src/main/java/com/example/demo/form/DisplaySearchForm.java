package com.example.demo.form;

import java.util.List;

import com.example.demo.entity.TbUser;
import com.example.demo.entity.Category;

import lombok.Data;

/**
 * 
 * Object transfer data between Form and Controller Validation from Form here
 * 
 * @author DoPT
 *
 *
 */

@Data
public class DisplaySearchForm {

	//Display list category
	private List<Category> category;
	
	//Display list booker
	private List<TbUser> booker;

}
