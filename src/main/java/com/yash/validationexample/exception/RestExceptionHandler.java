package com.yash.validationexample.exception;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yash.validationexample.exception.model.ValidationError;
import com.yash.validationexample.model.Response;
import com.yash.validationexample.validators.LifeInsuranceConditional;

import lombok.extern.slf4j.Slf4j;

import static com.yash.validationexample.utils.ApplicationConstants.VALIDATION_FAILED_FOR_GIVEN_REQUEST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@InitBinder
    private void initDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initBeanPropertyAccess();
    }
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> handleValidationException(ValidationException ex, WebRequest request) {
		log.error(VALIDATION_FAILED_FOR_GIVEN_REQUEST + " {} ", ex);
		 ResponseEntity<Object> validationResult = handleValidation(ex, ex.getResult(), request);
		  @SuppressWarnings("unchecked")
		Map<String, Object> body = (HashMap<String, Object>)validationResult.getBody();
		  Response response = new Response(HttpStatus.BAD_REQUEST, body);
		  return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> handleValidation(ValidationException ex, BindingResult result,
			WebRequest request) {
		List<FieldError> fieldErrors = result.getFieldErrors();
		ValidationError validationError = processFieldErrors(fieldErrors);
		Map<String, Object> errorPayload = new HashMap<String, Object>();
		
		for(Entry<String, Set<String>> error: validationError.getErrors().entrySet()) {
			for (String errorValue: error.getValue()) {
				if(errorValue.startsWith(NotEmpty.class.getSimpleName()) 
						|| errorValue.startsWith(NotNull.class.getSimpleName())
						|| errorValue.startsWith(NotBlank.class.getSimpleName())
						|| errorValue.startsWith(LifeInsuranceConditional.class.getSimpleName())
						) {
					validationError.addMandatoryField(error.getKey());
				}
			}
		}
		
		errorPayload.put("errors", validationError);
		
		return handleExceptionInternal(ex, errorPayload, new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);
	}

	private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
		ValidationError validationError = ValidationError.getInstance();
		for(FieldError fieldError: fieldErrors) {
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return validationError;
	}
}
