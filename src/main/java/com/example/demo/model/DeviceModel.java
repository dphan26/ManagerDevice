package com.example.demo.model;

import java.util.Date;

import com.example.demo.entity.TblUser;
import com.example.demo.entity.Category;
import com.example.demo.entity.Device;

import lombok.Data;

/**
 * 
 *  Object transfer data between Controller and Entity
 * @author DoPT
 *
 *
 */
@Data
public class DeviceModel {	
	
	
		//Device ID
		private String id;
		
		//Device Name
		private String deviceName;
		
		//version
		private String version;
		
		//borrowed Time
		private Date borrowedTime;
		
		//returned Time
		private Date returnedTime;	
		
		// status 
		private String status;
		
		//category name
		 private Category category;
		 
		 //booker
		 private TblUser user;
		 

		public DeviceModel(String id, String deviceName, String version, Date borrowedTime, Date returedTime,
				String status, Category category, TblUser user) {			
			this.id = id;
			this.deviceName = deviceName;
			this.version = version;
			this.borrowedTime = borrowedTime;
			this.returnedTime = returedTime;
			this.status = status;
			this.category = category;
			this.user = user;
		}


		public DeviceModel(Device obj) {
			this.id = obj.getId();
			this.deviceName =obj.getDeviceName();
			this.version = obj.getVersion();
			this.borrowedTime = obj.getBorrowedTime();
			this.returnedTime = obj.getReturnedTime();
			this.category = obj.getCategory();
			this.user = obj.getUser();
			this.status = obj.getStatus();
			// TODO Auto-generated constructor stub
		}


		
}

