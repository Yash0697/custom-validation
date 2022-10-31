package com.yash.validationexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.yash.validationexample.model.OccupationInfo;

public interface OccupationRepository extends CrudRepository<OccupationInfo, Integer> {

}
