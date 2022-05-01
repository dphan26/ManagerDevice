package com.example.demo.form;

import java.util.Date;

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
public class DeviceForm {

	// Device ID
	private String id;

	// Device Name
	private String deviceName;

	// version
	private String version;

	// borrowed Time
	private Date borrowedTime;

	// returned Time
	private Date returedTime;

	// status
	private String status;

	// category name
	private Category category;

	// booker
	private TblUser user;

}
