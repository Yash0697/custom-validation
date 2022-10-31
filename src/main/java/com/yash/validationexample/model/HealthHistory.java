package com.yash.validationexample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
public class HealthHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	String disease;
	
	boolean isCured;
	
	String organAffected;
	
}
