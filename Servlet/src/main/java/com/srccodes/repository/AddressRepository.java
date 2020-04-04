package com.srccodes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.srccodes.model.AddressModel;

@Repository
public interface AddressRepository extends MongoRepository<AddressModel, String>{
	public AddressModel findAddressModelById(String id);
}
