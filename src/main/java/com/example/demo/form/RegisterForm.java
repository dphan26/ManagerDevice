package com.example.demo.form;

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
public class RegisterForm {

	// Device ID
		private String deviceId;

		// Device Name
		private String deviceName;

		// categoryId
		private String categoryId;

		// version
		private String version;		
		
		// status
		private String site;
}
