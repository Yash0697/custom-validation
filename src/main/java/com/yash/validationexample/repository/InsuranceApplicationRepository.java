package com.yash.validationexample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.validationexample.model.InsuranceApplication;


@Repository
public interface InsuranceApplicationRepository extends CrudRepository<InsuranceApplication, Long> {

}
