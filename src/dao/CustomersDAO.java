package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Agent;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomersDAO {
	
	private static boolean executeCommand(String command){
    	Connection conn = DBHelper.getConnection();
    	try{
    		Statement stmt = conn.createStatement();
    		stmt.execute(command);
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
	
	// Get a list of all agents
		 public static ObservableList<Customer> getCustomers(){
			 
			 ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
			 Connection conn = DBHelper.getConnection();
			 
		    	try{
		    		Statement stmt = conn.createStatement();
		    		ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
		    		while(rs.next()){
		    			allCustomers.add(new Customer(
		    					rs.getString(1), 
		    					rs.getString(2), 
		    					rs.getString(3), 
		    					rs.getString(4), 
		    					rs.getString(5), 
		    					rs.getString(6), 
		    					rs.getString(7),
		    					rs.getString(8),
		    					rs.getString(9), 
		    					rs.getString(10),
		    					rs.getString(11), 
		    					rs.getString(13)));		    			
		    		}
		    		
		    	}
		    	catch(SQLException e){
		    		e.printStackTrace();
		    		return null;
		    	}
		    	return allCustomers;
		 }	
	
	// Update Specific Agent with the given ID and information
		public static boolean updateCustomer(
				String inputCustId,
				String inputCustFirstName,
				String inputCustLastName,
				String inputCustAddress,
				String inputCustCity,
				String inputCustProv,
				String inputCustPostal,
				String inputCustCountry,
				String inputCustHomePhone,
				String inputCustBusPhone,
				String inputCustEmail,
				String inputAgentId
				) {
			
			boolean success = false;
			
			String sqlStatement = "UPDATE customers SET"
			+ " CustFirstName='" + inputCustFirstName
			+ "', CustLastName='" + inputCustLastName
			+ "', CustAddress='" + inputCustAddress
			+ "', CustCity='" + inputCustCity
			+ "', CustProv='" + inputCustProv
			+"', CustPostal='"+ inputCustPostal
			+"', CustCountry='"+ inputCustCountry
			+"', CustHomePhone='"+ inputCustHomePhone
			+"', CustBusPhone='"+ inputCustBusPhone
			+"', CustEmail='"+ inputCustEmail
			+"', CustPassword='passsword'"
			+", AgentId="+ inputAgentId
			+" WHERE CustomerId="+ inputCustId;
			
			try {
				Connection conn = DBHelper.getConnection();
				success = executeCommand(sqlStatement);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return success;
		}
	
	public static boolean addCustomer(
			String inputCustFirstName,
			String inputCustLastName,
			String inputCustAddress,
			String inputCustCity,
			String inputCustProv,
			String inputCustPostal,
			String inputCustCountry,
			String inputCustHomePhone,
			String inputCustBusPhone,
			String inputCustEmail,
			String inputCustPassword,
			String inputAgentId
			) {
		boolean success = false;
		
		String sqlStatement = "INSERT INTO customers (CustFirstName, CustLastName, CustAddress, CustCity, CustProv, CustPostal, CustCountry, CustHomePhone, CustBusPhone, CustEmail, CustPassword, AgentId) VALUES ('"
			    + inputCustFirstName +"', '"
			    + inputCustLastName +"', '"
			    + inputCustAddress +"', '"
			    + inputCustCity +"', '"
			    + inputCustProv +"', '"
			    + inputCustPostal +"', '"
			    + inputCustCountry +"', '"
			    + inputCustHomePhone +"', '"
			    + inputCustBusPhone +"', '"
			    + inputCustEmail +"', '"
			    + inputCustPassword +"', "
			    + inputAgentId +")";
		
		try {
			success = executeCommand(sqlStatement);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return success;
	}
	
	public static boolean deleteCustomer(String inputCustId) {
		boolean success = false;
		String sqlStatement = "DELETE FROM customers WHERE CustomerId=" + inputCustId;
		
		try {
			success = executeCommand(sqlStatement);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
}
