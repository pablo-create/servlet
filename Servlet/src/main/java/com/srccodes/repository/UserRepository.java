package com.srccodes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.srccodes.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
	public UserModel findUserModelById(String id);
}