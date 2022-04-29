package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
public class BookingModel {

	// Device ID
	private String device_id;

	// Device Name
	private String deviceName;

	// version
	private String version;

	// borrowed Time
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date borrowedTime;

	// returned Time
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date returedTime;

	// remark
	private String remark;

	public BookingModel() {

	}

	public BookingModel(String device_id, String deviceName, String version, Date borrowedTime, Date returedTime,
			String remark) {
		this.device_id = device_id;
		this.deviceName = deviceName;
		this.version = version;
		this.borrowedTime = borrowedTime;
		this.returedTime = returedTime;
		this.remark = remark;
	}

}
