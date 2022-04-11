package com.example.demo.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<EmailValid, String>
{
	
		//https://www.baeldung.com/java-email-validation-regex
		private static final String PATTERN_EMAIL_SIMPLE = "^(.+)@(\\S+)$";
		private static final String PATTERN_EMAIL_DOMAIN = "^(.+)@(gmail.com.vn)$";
				
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {	
//	
//			return Pattern.compile(regexPattern)
//				      .matcher(emailAddress)
//				      .matches();
		  Pattern pattern = Pattern.compile(PATTERN_EMAIL_DOMAIN);
		  Matcher matcher = pattern.matcher(value);
		  if(matcher.matches()){
			  System.out.println("PATTERN_EMAIL "+ value +" is valid");
			  return true;
		  }else{
			  System.out.println("PATTERN_EMAIL "+ value +" is invalid");
			  return false;
		  }		  
		  
		}

}