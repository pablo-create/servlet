package com.srccodes.transformer;

import com.google.gson.Gson;
import com.srccodes.model.UserModel;

public class UserTransformer {
	private Gson gson = new Gson();
	
	public UserModel transformTo(String strUser) {
		return gson.fromJson(strUser, UserModel.class);
	}
	public String transformFrom(UserModel userModel) {
		return gson.toJson(userModel);
	}
}
