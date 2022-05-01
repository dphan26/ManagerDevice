package com.example.demo.form;

import java.util.List;

import com.example.demo.entity.TblUser;
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
public class SearchForm {

	// category name
	private List<Category> category;
	
	

}
