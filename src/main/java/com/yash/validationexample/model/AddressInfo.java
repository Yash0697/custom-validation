package com.yash.validationexample.model;

import static com.yash.validationexample.utils.ApplicationConstants.COUNTRY_CAN_NOT_BE_EMPTY;
import static com.yash.validationexample.utils.ApplicationConstants.PIN_CODE_CAN_NOT_BE_EMPTY;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class AddressInfo {
	

	@NotNull(message = PIN_CODE_CAN_NOT_BE_EMPTY)
	Integer pinCode;
	
	String street;
	
	String state;
	
	String city;
	
	@NotEmpty(message = COUNTRY_CAN_NOT_BE_EMPTY)
	String country;
}
