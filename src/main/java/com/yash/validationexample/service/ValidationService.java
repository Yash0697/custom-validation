package com.yash.validationexample.service;

import org.springframework.stereotype.Service;

import com.yash.validationexample.model.InsuranceApplication;
import com.yash.validationexample.model.Response;

@Service
public interface ValidationService {

	Response validateRequest(InsuranceApplication insuranceRequest);
}
