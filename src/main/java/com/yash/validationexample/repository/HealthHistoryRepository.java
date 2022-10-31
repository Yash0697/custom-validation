package com.yash.validationexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.yash.validationexample.model.HealthHistory;

public interface HealthHistoryRepository extends CrudRepository<HealthHistory, Integer> {

}
