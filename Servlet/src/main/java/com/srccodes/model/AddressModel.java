package com.srccodes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
public class AddressModel {
	@Id
	private String id;
	private String city;
	private String country;

	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
