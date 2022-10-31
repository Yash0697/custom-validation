package com.yash.validationexample.service;

import org.springframework.stereotype.Service;

import com.yash.validationexample.model.InsuranceApplication;
import com.yash.validationexample.model.Response;

@Service
public interface ApplicationService {

	Response processInsuranceApplicationRequest(InsuranceApplication application);
}
