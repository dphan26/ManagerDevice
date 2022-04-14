package com.example.demo.model;

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
public class RegisterModel {

	// Device ID
		private String deviceId;

		// Device Name
		private String deviceName;

		// categoryId
		private String categoryId;

		// version
		private String version;
		
		// status
		private String status;
		
		// status
		private String site;
}
