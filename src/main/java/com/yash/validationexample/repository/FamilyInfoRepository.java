package com.yash.validationexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.yash.validationexample.model.FamilyInfo;

public interface FamilyInfoRepository extends CrudRepository<FamilyInfo, Integer> {

}
