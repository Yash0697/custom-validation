package com.yash.validationexample.validators;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.yash.validationexample.model.LifeInsuranceApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LifeInsuranceConditionalValidator implements ConstraintValidator<LifeInsuranceConditional, Object> {

	private static final String DOT = ".";

	private String field;
	private String[] notRequired;
	private String fieldValue;
	private String message;

	@Override
	public void initialize(LifeInsuranceConditional obj) {
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

			if (actualCompanyId.equals(this.fieldValue))
				return true;
			for (String propertyName : notRequired) {
				String property = null;
				// occupationInfo is a list, so occupationInfo.companyName will throw no such
				// method exception
				if (propertyName.contains(DOT)) {
					valid = handleListType(propertyName, object, context);
				} else {
					property = BeanUtils.getProperty(object, propertyName);
					valid = property != null && property.length() > 0;
				}
				if (!valid) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(message).addPropertyNode(propertyName)
							.addConstraintViolation();
				}
			}

		} catch (IllegalAccessException e) {
			log.error("IllegalArgumentException during custom required validation of insurance request ", e);
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException during custom required validation of insurance request ", e);
		} catch (NoSuchMethodException e) {
			log.error("NoSuchMethodException during custom required validation of insurance request ", e);
		} catch (SecurityException e) {
			log.error("NoSuchFieldException during custom required validation of insurance request ", e);
		}

		return valid;
	}

	// This method expects that propertyName lis like parent.child
	private boolean handleListType(String propertyName, Object object, ConstraintValidatorContext context) {
		boolean valid = false;
		try {
			if (propertyName.contains(DOT)) {
				String[] propertyHeirarchy = propertyName.split("\\.");
				String propertyHeirarchyLevelOne = propertyHeirarchy[0];

				Field[] fields = LifeInsuranceApplication.class.getDeclaredFields();
				Field appField = null;
				for (Field declaredField : fields) {
					if (declaredField.getName().equals(propertyHeirarchyLevelOne.substring(0, propertyHeirarchyLevelOne.length() - 3))) {
						appField = declaredField;
						appField.setAccessible(true);
						break;
					}
				}
				if (List.class.isAssignableFrom(appField.getType())) {
					@SuppressWarnings("rawtypes")
					List list = (List) PropertyUtils.getProperty(object, propertyHeirarchyLevelOne.substring(0, propertyHeirarchyLevelOne.length() - 3));
					if(list.size() > 0) {
						 Character first = propertyHeirarchyLevelOne.charAt(0);
						 String camelCase = Character.toUpperCase(first) + new StringBuilder(propertyHeirarchyLevelOne.substring(0, propertyHeirarchyLevelOne.length() - 3)).deleteCharAt(0).toString();
						 
						 for(Object item: list) {
							 String propertyValue = BeanUtils.getProperty(item, propertyHeirarchy[1]);
							 if(propertyValue == null || propertyValue.length() == 0)
								 return false;
						 }
						 valid = true;
						 System.out.println(list.get(0).toString());
					}
				}

			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			log.error("Exception during handling of list property ", e);
		}
		return valid;
	}
}
