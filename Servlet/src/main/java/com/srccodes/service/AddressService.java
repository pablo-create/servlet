package com.srccodes.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.srccodes.model.AddressModel;
import com.srccodes.repository.AddressRepository;
import com.srccodes.transformer.AddressTransformer;

@Service
public class AddressService {
	
	private AddressModel addressModel;
	private AddressTransformer addressTransform  = new AddressTransformer();
	private String idString;
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	MongoTemplate mongoTemplate;	
	
	public String insert(String addressModelString) {
		MongoCollection<Document> collection = mongoTemplate.getCollection("User");
		FindIterable<Document> docs = collection.find();
		
		addressModel= addressTransform.transformTo(addressModelString);
		
		idString = Integer.toString(((int)(Math.random() *1000)));
		
		for(Document doc : docs) 
			if(doc.getString("id") == idString) {
				idString = Integer.toString(((int)(Math.random() *1000)));
				insert(addressModelString);
			}
		
		addressModel.setId(idString);
		addressRepository.insert(addressModel);
		return idString;
	}
	
	public String update(String id, String addressModelString){
		addressModel= addressTransform.transformTo(addressModelString);
		if(addressRepository.existsById(id)) {
		addressRepository.deleteById(id);
		addressModel.setId(id);
		addressRepository.save(addressModel);
		return "The address " + id + " was updated!";
		}
		else return "The address " + id + " is not exist!";
	}
	
	public String delete(String id) {
		if(addressRepository.existsById(id)) {
			addressRepository.deleteById(id);
			return "The address " + id + " was deleted!";
		}
		else return "The address " + id + " is not exist!";
	}
	
	public String select(String id) {
		if(addressRepository.existsById(id))
			return addressTransform.transformFrom(addressRepository.findAddressModelById(id));
			else return "The address " + id + " is not exist!";
	}
}
