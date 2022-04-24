/**
 * 
 */
package com.example.demo.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;


@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {DateRangeValidator.class} )
@Documented

public @interface ValidateDateRange {
    String message() default "{end} should be later than {start}";
    String start();
    String end();
    Class[] groups() default {};
    Class[] payload() default {};       
}