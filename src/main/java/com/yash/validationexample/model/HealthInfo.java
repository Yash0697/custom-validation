package com.yash.validationexample.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class HealthInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;

	@NotNull
	Double weight;
	
	@NotNull
	Double height;
	
	@NotNull
	Double age;
	
	@NotEmpty
	String bloodGroup;
	
	@NotNull
	Double bloodPressure;
	@OneToMany(targetEntity=HealthHistory.class, mappedBy="id", fetch=FetchType.LAZY)
	List<@Valid HealthHistory> healthHistory;
	
}
