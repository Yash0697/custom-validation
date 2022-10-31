package com.yash.validationexample.model;

import static com.yash.validationexample.utils.ApplicationConstants.OCCUPATION_INFO_CAN_NOT_BE_EMPTY;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.yash.validationexample.validators.LifeInsuranceConditional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Entity
@LifeInsuranceConditional(field = "companyId", fieldValue = "HDFC", notRequired = {"occupationInfo.companyName"}, message = "comanyName is required for insureance provider HDFC")
public class LifeInsuranceApplication extends InsuranceApplication {

	@NotNull(message = OCCUPATION_INFO_CAN_NOT_BE_EMPTY)
	@OneToMany(targetEntity=OccupationInfo.class, mappedBy="id", fetch=FetchType.LAZY)
	List<@Valid OccupationInfo> occupationInfo;
	
	@Valid
	AddressInfo addressInfo;
	
	@OneToMany(targetEntity=FamilyInfo.class, mappedBy="id", fetch=FetchType.LAZY)
	List<@Valid FamilyInfo> familyInfo;
	
	@Valid
	@OneToOne
	HealthInfo healthInfo;
}
