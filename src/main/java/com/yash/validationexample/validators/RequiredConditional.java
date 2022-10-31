package com.yash.validationexample.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Repeatable(RequiredConditional.CompanyIdConditionals.class)
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER, CONSTRUCTOR})
@Constraint(validatedBy = RequiredValidator.class)
@Documented
public @interface RequiredConditional {

	String field();
	String[] notRequired();
	String fieldValue();
	String message() default "This field is not required only HDFC";
	Class<?>[] groupd() default{};
	Class<? extends Payload>[] payload() default {};
	
	@Retention(RUNTIME)
	@Target({TYPE, METHOD, FIELD, PARAMETER, CONSTRUCTOR})
	@Documented
	@interface CompanyIdConditionals {
		RequiredConditional[] value();
	}
	
}
