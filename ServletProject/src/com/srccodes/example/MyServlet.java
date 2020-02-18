package com.srccodes.example;

import java.io.*;
import java.util.*;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;



@WebServlet(urlPatterns = {"/user/api/*","/address/api/*"})

public class MyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
    }
    
	public String strUser= new String();//The string for writing our request
	public String strAddress= new String();//The string for writing our request
	public Map<Integer,String> mapUser = new HashMap<Integer,String>();
	public Map<Integer,String> mapAddress = new HashMap<Integer,String>();
	public URL url;
	public int id;
	public int userCounter=0;//It is used in our responses when We add new User
	public int addressCounter=0;//It is used in our responses when We add new Address

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
	
		StringBuffer strBuf = new StringBuffer(request.getRequestURL());//It is similar to StringBuilder class
		BufferedWriter writer = new BufferedWriter(response.getWriter());
		url = new URL(strBuf.toString());

		String [] getURL_ID = strBuf.toString().split("/");//We are getting id from our URL
	 	id=Integer.parseInt(getURL_ID[6]);
	 	
	 	//Writing JSON to the OutputStream
		if(url.getPath().contains("/user/api/")) {
				writer.write(mapUser.get(id).toString());
				writer.close();
			}
				else if(url.getPath().contains("/address/api/")){
					writer.write(mapAddress.get(id).toString());
					writer.close();
				}	
	}		

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json; charset=utf-8");
		 
		 strUser = "";
		 strAddress = "";
		 
		 //Reading URL from the InputStream
		 StringBuffer strBuf = new StringBuffer(request.getRequestURL());//It is similar to StringBuilder class
		 url = new URL(strBuf.toString());
		 //Reading JSON from the InputStream and after that writing to the OutputStream
		 BufferedReader reader = new BufferedReader(request.getReader());		
		 BufferedWriter writer = new BufferedWriter(response.getWriter());

		 postMethod : {
		 if(url.getPath().contains("/user/api")) { 
		 		while(reader.ready())
		 			strUser+=reader.readLine() + "\n";

		 		if(mapUser.size()==0) { //I have done this because I didn`t want to write the same string in the collection
		 			mapUser.put(userCounter,strUser);
		 			writer.write("{ \"id\": " + userCounter++ +"}");//Writing response to the Postman
					writer.close();
		 		}
		 		else if(mapUser.containsValue(strUser)) break postMethod;//Check whether our String is inside of the collection or not	
		 			else { 
		 				mapUser.put(userCounter,strUser);
		 				writer.write("{ \"id\": " + userCounter++ +"}");//Writing response to the Postman
						writer.close();
						System.out.println("Hello");
		 				break postMethod;
		 			}
		 	}
				else if(url.getPath().contains("/address/api")) {
				while(reader.ready())
					strAddress+=reader.readLine() + "\n";

				if(mapAddress.size()==0) {//I have done this because I didn`t want to write the same string in the collection
					mapAddress.put(addressCounter,strAddress);
					writer.write("{ \"id\": " + addressCounter++ +"}");//Writing response to the Postman
					writer.close();
				}
				else if(mapAddress.containsValue(strAddress)) break postMethod;//Check whether our String is inside of the collection or not
		 			else {
		 				mapAddress.put(addressCounter,strAddress);
		 				writer.write("{ \"id\": " + addressCounter++ +"}");//Writing response to the Postman
						writer.close();
		 				break postMethod;
		 			}
				}						
		 reader.close();
		 } 
	 }
	 
	 protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 strUser = "";
		 strAddress = "";
		 
		 StringBuffer strBuf = new StringBuffer(request.getRequestURL());//It is similar to StringBuilder class
		 String [] getURL_ID = strBuf.toString().split("/");//We are getting id from our URL
		 BufferedReader reader = new BufferedReader(request.getReader());
		 id=Integer.parseInt(getURL_ID[6]);	
		 
		 if(url.getPath().contains("/user/api/")) {
			 while(reader.ready())
		 			strUser+=reader.readLine() + "\n";
				mapUser.replace(id,strUser);

			}
				else if(url.getPath().contains("/address/api/")){
					while(reader.ready())
			 			strAddress+=reader.readLine() + "\n";
					mapAddress.replace(id,strAddress);
				}	
	 }
	 
	 protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 StringBuffer strBuf = new StringBuffer(request.getRequestURL());//It is similar to StringBuilder class
		 String [] getURL_ID = strBuf.toString().split("/");//We are getting id from our URL
		 id=Integer.parseInt(getURL_ID[6]);	
		 
		 if(url.getPath().contains("/user/api/")) {
				mapUser.remove(id);

			}
				else if(url.getPath().contains("/address/api/")){
					mapAddress.remove(id);
				}	
	 }	
}

