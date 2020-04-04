package com.srccodes.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.srccodes.model.UserModel;
import com.srccodes.repository.UserRepository;
import com.srccodes.transformer.UserTransformer;

@Service
public class UserService {
	
	private UserModel userModel ;
	private UserTransformer userTransform  = new UserTransformer();
	private String idString;
	
	@Autowired
	UserRepository userRepository;	
	@Autowired
	MongoTemplate mongoTemplate;
	
	 
	public String insert(String userModelString) {
		MongoCollection<Document> collection = mongoTemplate.getCollection("User");
		FindIterable<Document> docs = collection.find();
		
		userModel= userTransform.transformTo(userModelString);
	
		idString = Integer.toString(((int)(Math.random() *1000)));
		
		for(Document doc : docs) 
			if(doc.getString("id") == idString) {
				idString = Integer.toString(((int)(Math.random() *1000)));
				insert(userModelString);
			}
		
		userModel.setId(idString);
		userRepository.insert(userModel);
		return idString;
		}	
		
	public String update(String id, String userModelString){
		userModel= userTransform.transformTo(userModelString);
		if(userRepository.existsById(id)) {
		userRepository.deleteById(id);
		userModel.setId(id);
		userRepository.save(userModel);
		return "The user " + id + " was updated!";
		}
		else return "The user " + id + " is not exist!";
	}
	
	public String delete(String id) {
		if(userRepository.existsById(id)) {
		userRepository.deleteById(id);
		return "The user " + id + " was deleted!";
		}
		else return "The user " + id + " is not exist!";
	}
	
	public String select(String id) {
		if(userRepository.existsById(id))
		return userTransform.transformFrom(userRepository.findUserModelById(id));
		else return "The user " + id + " is not exist!";
	}
	
}
