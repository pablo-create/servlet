package Transformer;

import com.google.gson.Gson;

import Model.UserModel;

public class UserTransformer {
	private Gson gson = new Gson();
	
	public UserModel transform(String strUser) {
		return gson.fromJson(strUser, UserModel.class);
	}
}
