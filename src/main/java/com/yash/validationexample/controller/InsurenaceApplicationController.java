package com.yash.validationexample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.validationexample.model.InsuranceApplication;
import com.yash.validationexample.model.Response;
import com.yash.validationexample.service.ApplicationService;
import com.yash.validationexample.service.ValidationService;
import com.yash.validationexample.utils.EndPointConstants;

@RestController
@RequestMapping(EndPointConstants.APPLICATION)
public class InsurenaceApplicationController {

	@Autowired
	ValidationService validationService;

	@Autowired
	ApplicationService applicationService;

	@PostMapping(EndPointConstants.INSURANCE_REQUEST)
	ResponseEntity<Response> processInsurenaceRequest(@RequestBody InsuranceApplication application) {

		Response response = validationService.validateRequest(application);

		if (response == null) {
			Response insuranceApplication = applicationService.processInsuranceApplicationRequest(application);
			return new ResponseEntity<Response>(insuranceApplication, HttpStatus.CREATED);
		}
		Map<String, Object> payload = new HashMap<>();
		payload.put("ERROR", "Please contact your developers");
		Response noRes = new Response(HttpStatus.NO_CONTENT, payload);
		return new ResponseEntity<Response>(noRes, HttpStatus.NO_CONTENT);
	}
}
