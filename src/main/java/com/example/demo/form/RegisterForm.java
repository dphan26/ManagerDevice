package com.example.demo.form;



import javax.validation.constraints.NotBlank;

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
		@NotBlank(message = "deviceId is mandatory")		
		private String deviceId;

		// Device Name
		@NotBlank(message = "deviceName is mandatory")
		private String deviceName;

		// categoryId
		@NotBlank(message = "categoryId is mandatory")
		private String categoryId;

		// version
		@NotBlank(message = "version is mandatory")
		private String version;		
		
		// site
		@NotBlank(message = "site is mandatory")
		private String site;
}
