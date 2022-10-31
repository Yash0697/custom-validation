package com.yash.validationexample.validators;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequiredValidator implements ConstraintValidator<RequiredConditional, Object> {

	
	private static final String DOT = ".";
	
	private String field;
	private String[] notRequired;
	private String fieldValue;
	private String message;
	
	@Override
	public void initialize(RequiredConditional obj) {
		field = obj.field();
		message = obj.message();
		notRequired = obj.notRequired();
		fieldValue = obj.fieldValue();
		
	}
 
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean valid = false;
		
		try {
			String actualCompanyId = BeanUtils.getProperty(object, field);
			
			if(actualCompanyId.equals(this.fieldValue)) 
				return true;
			for(String propertyName: notRequired) {
				String property = null;
				if(propertyName.contains(DOT))
					property = BeanUtils.getNestedProperty(object, propertyName);
				else
					property = BeanUtils.getProperty(object, propertyName);
				valid = property != null && property.length() > 0;
				if(!valid) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(message).addPropertyNode(propertyName).addConstraintViolation();
				}
			}
			
		} catch (IllegalAccessException e) {
			log.error("IllegalArgumentException during custom required validation of insurance request ", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException during custom required validation of insurance request ", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException during custom required validation of insurance request ", e);
		}
		
		return false;
	}

}
