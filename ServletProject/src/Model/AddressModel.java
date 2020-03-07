package Model;

public class AddressModel {
	private String city;
	private String country;
	
	public AddressModel(String city, String country) {
		this.city = city;
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
}
