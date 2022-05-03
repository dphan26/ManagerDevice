package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DEVICES")
public class Device {
	@Id
	@Column(name = "ID", nullable = false)
	private String id;

	@Column(name = "DEVICE_NAME")
	private String deviceName;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "BORROWED_TIME")
	private Date borrowedTime;

	@Column(name = "RETURNED_TIME")
	private Date returnedTime;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "SITE")
	private String site;
	
	@Column(name = "REMARK")
	private String remark;

	// FOREIGN KEY
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "BOOKER_ID")
	private TblUser user;

	public Device() {
	}

	public Device(String id, Date borrowedTime, Date returnedTime, String status, String remark) {
		this.id = id;
		this.borrowedTime = borrowedTime;
		this.returnedTime = returnedTime;
		this.status = status;
		this.remark = remark;
	}

	public Device(String id, String deviceName, String version, Date borrowedTime, Date returnedTime, String status,
			String site, String remark, Category category, TblUser user) {
		this.id = id;
		this.deviceName = deviceName;
		this.version = version;
		this.borrowedTime = borrowedTime;
		this.returnedTime = returnedTime;
		this.status = status;
		this.site = site;
		this.remark = remark;
		this.category = category;
		this.user = user;
	}
}
