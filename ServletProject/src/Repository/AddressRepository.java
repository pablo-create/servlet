package Repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;

import Model.AddressModel;

public class AddressRepository {
	
	private String urlJDBC = "jdbc:mysql://localhost:3306/first_schema";
	private String username = "root";
	private String password = "mydatabase";
	private int rez =0;//It is used when we need to return the message about successful or unsuccessful operation
	private Gson gson = new Gson();
	
	public void addressInsert(PrintWriter writer, AddressModel addressModel) {
		 try{  
	            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
	              Statement statement = conn.createStatement();
	                statement.executeUpdate("INSERT Addresses(country, city) VALUES "
	                		+ "('" + addressModel.getCountry() + "','" +addressModel.getCity() + "')");
	              ResultSet rowId = statement.executeQuery("SELECT LAST_INSERT_ID();");//Getting the last added row`s(address`s) id
	              if(rowId.next())
	               writer.write("{ \"id\" : " + rowId.getInt(1) + "\n}");
	              conn.close();
	            }
	        }
	        catch(Exception ex){
	            writer.println("Connection failed...");
	            writer.println(ex);
	        }
	        finally {        	
	            writer.close();
	        }
	}
	
	public void addressSelect(PrintWriter writer, AddressModel addressModel,int id) {
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
              ResultSet addressData = statement.executeQuery("SELECT * FROM Addresses WHERE id = " + id);//Getting user`s values for the id
              while(addressData.next()) //Values of chosen address are written to the object AddressModel after that they converting to JSON 
              writer.write(gson.toJson(new AddressModel(addressData.getString("country"), addressData.getString("city"))));             
              conn.close();
            }
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {        	
           writer.close();
        }
	}
	
	public void addressDelete(PrintWriter writer, AddressModel addressModel,int id) {
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
              rez = statement.executeUpdate("DELETE FROM Addresses WHERE id = " + id);
              if(rez == 0) writer.write("Something went wrong!");
              else if(rez == 1) writer.write("The address was successfully deleted!");
              conn.close();
            }
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {        	
           writer.close();
        }
	}
	
	public void addressUpdate(PrintWriter writer, AddressModel addressModel,int id) {
		
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
              rez = statement.executeUpdate("UPDATE Addresses SET country= '"+ addressModel.getCountry() 
              							+ "', city= '"+ addressModel.getCity() + "' WHERE id = " + id);
              if(rez == 0) writer.write("Something went wrong!");
              else if(rez == 1) writer.write("The address was successfully updated!");
              conn.close();
            }
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {        	
           writer.close();
        }
	}
	}
