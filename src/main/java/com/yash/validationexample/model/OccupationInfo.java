package com.yash.validationexample.model;

import static com.yash.validationexample.utils.ApplicationConstants.OCCUPATION_NATURE_CAN_NOT_BE_EMPTY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
public class OccupationInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;

	@NotEmpty(message = OCCUPATION_NATURE_CAN_NOT_BE_EMPTY)
	String occupationNature;
	
	String designation;
	
	Long yearsOfOccupation;
	
	String typeOfCompany;
	
	String companyName;

	@Override
	public String toString() {
		return "OccupationInfo [id=" + id + ", occupationNature=" + occupationNature + ", designation=" + designation
				+ ", yearsOfOccupation=" + yearsOfOccupation + ", typeOfCompany=" + typeOfCompany + ", companyName="
				+ companyName + "]";
	}
	
	
	
}
