package com.yash.validationexample.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.yash.validationexample.model.InsuranceApplication;
import com.yash.validationexample.exception.ValidationException;
import com.yash.validationexample.model.Response;
import com.yash.validationexample.service.ApplicationService;
import com.yash.validationexample.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	@Autowired
	private LocalValidatorFactoryBean validator;
	
	@Autowired
	ApplicationService applicationService;

	@Override
	public Response validateRequest(InsuranceApplication insuranceRequest) {
		BindingResult result = new BindException(insuranceRequest, InsuranceApplication.class.getSimpleName());
		validator.validate(insuranceRequest, result);
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
		return null;
	}

}
