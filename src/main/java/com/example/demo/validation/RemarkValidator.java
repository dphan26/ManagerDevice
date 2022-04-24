package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class RemarkValidator implements ConstraintValidator<RemarkValid, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isNotBlank(value)) {
			if (10 <= value.length() && value.length() <= 50) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}