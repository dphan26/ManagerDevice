package com.example.demo.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IpAddressValidator implements ConstraintValidator<IpAddress, String>
{
	private static final String PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	//format 11.144.xx.yy  
		private static final String PATTERN_IP = 
				"^(11)\\." +
				"(144)\\." +
				"(\\d\\d)\\." +
				"(\\d\\d)$";

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			// TODO Auto-generated method stub
//			  List<String> values = new ArrayList<String>();	
//			values.add("255.255.255.255"); 
//			values.add("127.0.0.1"); 
//			values.add("10.10");
//			values.add("10.10.10.256");
		  
		  Pattern pattern = Pattern.compile(PATTERN_IP);
		  Matcher matcher = pattern.matcher(value);
		  if(matcher.matches()){
			  System.out.println("IP "+ value +" is valid");
			  return true;
		  }else{
			  System.out.println("IP "+ value +" is invalid");
			  return false;
		  }		  
		  
		}
	


//  @Override
//  public boolean isValid(Object value, ConstraintValidatorContext cvContext)
//  {
//
//	  List<String> values = new ArrayList<String>();	
//		values.add("255.255.255.255"); 
//		values.add("127.0.0.1"); 
//		values.add("10.10");
//		values.add("10.10.10.256");
//	  
//	  Pattern pattern = Pattern.compile(PATTERN_IP);
//		for (String value1 : values){
//		  Matcher matcher = pattern.matcher(value1);
//		  if(matcher.matches()){
//			  System.out.println("IP "+ value1 +" is valid");
//		  }else{
//			  System.out.println("IP "+ value1 +" is invalid");
//		  }		  
//		}
//	  return false;
//    // logic here
//  }
}