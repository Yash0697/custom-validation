package com.yash.validationexample.model;

import static com.yash.validationexample.utils.ApplicationConstants.INSURANCE_PROVIDER_CAN_NOT_BE_EMPTY;
import static com.yash.validationexample.utils.ApplicationConstants.INSURANCE_TYPE_CAN_NOT_BE_EMPTY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
               @JsonSubTypes.Type(value = LifeInsuranceApplication.class)
})
@Entity
@Table(name = "INSURANCE_APPLICATION")
public class InsuranceApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "REFERENCE_ID",updatable = false, nullable = false)
	Long referenceId;
	
	@NotEmpty(message = INSURANCE_PROVIDER_CAN_NOT_BE_EMPTY)
	String companyId;
	
	@JsonProperty("@type")
	@NotEmpty(message = INSURANCE_TYPE_CAN_NOT_BE_EMPTY)
	String insuranceType;
		
	
}
