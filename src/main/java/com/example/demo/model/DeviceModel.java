package com.example.demo.model;

import java.util.Date;

import com.example.demo.entity.TbUser;
import com.example.demo.entity.Category;

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
		private Date returedTime;	
		
		// status 
		private String status;
		
		//category name
		 private Category category;
		 
		 //booker
		 private TbUser booker;

		public DeviceModel() {
			
		}

		public DeviceModel(String id, String deviceName, String version, Date borrowedTime, Date returedTime,
				String status, Category category, TbUser booker) {			
			this.id = id;
			this.deviceName = deviceName;
			this.version = version;
			this.borrowedTime = borrowedTime;
			this.returedTime = returedTime;
			this.status = status;
			this.category = category;
			this.booker = booker;
		}
		
}

