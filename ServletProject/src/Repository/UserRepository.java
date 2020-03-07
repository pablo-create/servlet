package Repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.UserModel;

public class UserRepository {
	private String urlJDBC = "jdbc:mysql://localhost:3306/first_schema";
	private String username = "root";
	private String password = "mydatabase";
	private int rez =0;//It is used when we need to return the message about successful or unsuccessful operation
	private Gson gson = new Gson();
	
	public void userInsert(PrintWriter writer,UserModel userModel) {
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
                statement.executeUpdate("INSERT Users(firstName, lastName) VALUES "
                		+ "('" + userModel.getFirstName() + "','" +userModel.getLastName() + "')");
              ResultSet rowId = statement.executeQuery("SELECT LAST_INSERT_ID();");//Getting the last added row`s(user`s) id
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
	
	public void userSelect(PrintWriter writer,int id) {
		
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
              ResultSet userData = statement.executeQuery("SELECT * FROM Users WHERE id = " + id);//Getting user`s values for the id
              while(userData.next()) //Values of chosen user are written to the object UserModel after that they converting to JSON 
              writer.write(gson.toJson(new UserModel(userData.getString("firstName"), userData.getString("lastName"))));             
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
	
	public void userDelete(PrintWriter writer,int id) {
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
              rez = statement.executeUpdate("DELETE FROM Users WHERE id = " + id);
              if(rez == 0) writer.write("Something went wrong!");
              else if(rez == 1) writer.write("The user was successfully deleted!");
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
	
	public void userUpdate(PrintWriter writer,int id,UserModel userModel) {
		try{  
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(urlJDBC, username, password)){
              Statement statement = conn.createStatement();
              rez = statement.executeUpdate("UPDATE Users SET firstName= '"+ userModel.getFirstName() 
              					+ "', lastName= '"+ userModel.getLastName() + "' WHERE id = " + id);
              if(rez == 0) writer.write("Something went wrong!");
              else if(rez == 1)writer.write("The user was successfully updated!");
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
