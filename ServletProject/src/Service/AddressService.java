package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.AddressModel;
import Repository.AddressRepository;
import Transformer.AddressTransformer;

public class AddressService {
	
	private  AddressModel addressModel;
	private AddressTransformer transformer = new AddressTransformer();
	private AddressRepository addressRepository =new AddressRepository();
	
	public void insert(PrintWriter writer,BufferedReader reader,HttpServletResponse response) throws IOException {		//Adding users to the database
		response.setContentType("application/json; charset=utf-8");	
		
		//Converting from JSON to the java object and reading from InputStream in readJSON()
		addressModel = transformer.transform(readJSON(reader));
		
	 	//Connecting to the database and working with it
        addressRepository.addressInsert(writer,addressModel);
	}
	
	
	public void update(StringBuffer strBuf, PrintWriter writer,BufferedReader reader) throws IOException {
		int id = getUrlId(strBuf);
		
		//Converting from JSON to the java object and reading from InputStream in readJSON()
		addressModel = transformer.transform(readJSON(reader));
		
		//Connecting to the database and working with it
        addressRepository.addressUpdate(writer,addressModel,id);
	}
	
	
	public void delete(StringBuffer strBuf,PrintWriter writer) {
		int id = getUrlId(strBuf); //Getting id from URL
		
		//Connecting to the database and working with it
        addressRepository.addressDelete(writer,addressModel,id);
	}
	public void select(StringBuffer strBuf, PrintWriter writer,HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");	
		
		int id =getUrlId(strBuf); //Getting id from URL
		
		//Connecting to the database and working with it
        addressRepository.addressSelect(writer, addressModel, id);
	}
	
		//Method which reads JSON from InputSteam
	private String readJSON(BufferedReader reader) throws IOException {
		String strAddress = new String();
		while(reader.ready())
			strAddress+=reader.readLine() + "\n";
	 	reader.close();
	 	return strAddress;
	}
	
		//Method which gets id from URL
	private int getUrlId(StringBuffer strBuf) {
		String [] getURL_ID = strBuf.toString().split("/");//We are getting id from our URL
	 	int id=Integer.parseInt(getURL_ID[6]);
	 	return id;
	}
}
