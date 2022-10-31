package com.yash.validationexample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.validationexample.model.HealthInfo;

@Repository
public interface HealthInfoRepository extends CrudRepository<HealthInfo, Integer> {

}
