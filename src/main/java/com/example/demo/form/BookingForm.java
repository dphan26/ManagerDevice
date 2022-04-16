package com.example.demo.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
public class BookingForm {

	// Device ID
	private String device_id;

	// Device Name
	private String deviceName;

	// version
	private String version;

	// borrowed Time
	@NotNull(message = "borrowedTime not empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date borrowedTime;

	// returned Time
	@NotNull(message = "returedTime not empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date returedTime;

	// remark
	@NotBlank(message = "remark not empty")
	private String remark;

	public BookingForm() {

	}

	public BookingForm(String device_id, String deviceName, String version, Date borrowedTime, Date returedTime,
			String remark) {
		this.device_id = device_id;
		this.deviceName = deviceName;
		this.version = version;
		this.borrowedTime = borrowedTime;
		this.returedTime = returedTime;
		this.remark = remark;
	}

}
