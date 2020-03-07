package Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Transformer.UserTransformer;
import Model.UserModel;
import Repository.UserRepository;

import java.sql.*;

public class UserService {
	
	private UserTransformer transformer = new UserTransformer();
	private UserRepository userRepository = new UserRepository();
	private  UserModel userModel;
	
	public void insert(PrintWriter writer,BufferedReader reader,HttpServletResponse response) throws IOException {		//Adding users to the database
		response.setContentType("application/json; charset=utf-8");
		//Converting from JSON to the java object and reading from InputStream in readJSON()
		userModel = transformer.transform(readJSON(reader));
		
	 	//Connecting to the database and working with it
		userRepository.userInsert(writer,userModel);
	}
	
	
	public void update(StringBuffer strBuf, PrintWriter writer,BufferedReader reader) throws IOException {
		int id = getUrlId(strBuf);
		
		//Converting from JSON to the java object and reading from InputStream in readJSON()
		userModel = transformer.transform(readJSON(reader));
		
		//Connecting to the database and working with it
		userRepository.userUpdate(writer,id,userModel);
		
	}
	
	
	public void delete(StringBuffer strBuf,PrintWriter writer) {
		int id = getUrlId(strBuf); //Getting id from URL
		
		//Connecting to the database and working with it
		userRepository.userDelete(writer, id);
	}
	public void select(StringBuffer strBuf, PrintWriter writer,HttpServletResponse response) {
		response.setContentType("application/json; charset=utf-8");
		int id =getUrlId(strBuf); //Getting id from URL
		
		//Connecting to the database and working with it
		userRepository.userSelect(writer,id);
	}
	
		//Method which reads JSON from InputSteam
	private String readJSON(BufferedReader reader) throws IOException {
		String strUser = new String();
		while(reader.ready())
 			strUser+=reader.readLine() + "\n";
	 	reader.close();
	 	return strUser;
	}
	
		//Method which gets id from URL
	private int getUrlId(StringBuffer strBuf) {
		String [] getURL_ID = strBuf.toString().split("/");//We are getting id from our URL
	 	int id=Integer.parseInt(getURL_ID[6]);
	 	return id;
	}
}
