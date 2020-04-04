package com.srccodes.transformer;

import com.google.gson.Gson;
import com.srccodes.model.AddressModel;

public class AddressTransformer {
private Gson gson = new Gson();
	
	public AddressModel transformTo(String strAddress) {
		return gson.fromJson(strAddress, AddressModel.class);
	}
	public String transformFrom(AddressModel addressModel) {
		return gson.toJson(addressModel);
	}
}
