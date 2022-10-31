package com.yash.validationexample.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.yash.validationexample.model.InsuranceApplication;
import com.yash.validationexample.model.Response;
import com.yash.validationexample.repository.InsuranceApplicationRepository;
import com.yash.validationexample.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	InsuranceApplicationRepository insuranceApplicationRepository;
	@Override
	public Response processInsuranceApplicationRequest(InsuranceApplication application) {
		InsuranceApplication savedApplication = insuranceApplicationRepository.save(application);
		if(savedApplication != null) {
			Map<String, Object> payload = new HashMap<>();
			payload.put("application", savedApplication);
			return new Response(HttpStatus.CREATED, payload);
		}
		return null;
	}

}
