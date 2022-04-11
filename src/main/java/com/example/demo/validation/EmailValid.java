/**
 * 
 */
package com.example.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValid
{
  String message() default "{email.format}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}