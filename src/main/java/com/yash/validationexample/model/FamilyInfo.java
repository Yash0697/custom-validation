package com.yash.validationexample.model;

import static com.yash.validationexample.utils.ApplicationConstants.RELATION_WITH_APPLICANT_CAN_NOT_BE_EMPTY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class FamilyInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@NotEmpty(message = RELATION_WITH_APPLICANT_CAN_NOT_BE_EMPTY)
	String relation;
	
	boolean isBloodRelative;
}
