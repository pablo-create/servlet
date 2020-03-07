package Transformer;

import com.google.gson.Gson;
import Model.AddressModel;

public class AddressTransformer {
private Gson gson = new Gson();
	
	public AddressModel transform(String strAddress) {
		return gson.fromJson(strAddress, AddressModel.class);
	}
}
