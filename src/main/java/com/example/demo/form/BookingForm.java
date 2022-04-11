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
public class BookingForm {

	// Device ID
		private String deviceId;

		// Device Name
		private String deviceName;

		// version
		private String version;

		// borrowed Time
		private String borrowedTime;

		// returned Time
		private String returedTime;

		// status
		private String remark;

		public BookingForm() {

		}

		public BookingForm(String deviceId, String deviceName, String version, String borrowedTime, String returedTime,
				String remark) {
			this.deviceId = deviceId;
			this.deviceName = deviceName;
			this.version = version;
			this.borrowedTime = borrowedTime;
			this.returedTime = returedTime;
			this.remark = remark;
		}




}
