package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Agent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AgentsDAO {
	
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
	 public static ObservableList<Agent> getAgents(){
		 
		 ObservableList<Agent> allAgents = FXCollections.observableArrayList();
		 Connection conn = DBHelper.getConnection();
		 
	    	try{
	    		Statement stmt = conn.createStatement();
	    		ResultSet rs = stmt.executeQuery("SELECT * FROM agents");
	    		while(rs.next()){
	    			allAgents.add(new Agent(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
	    			System.out.println(rs.getString(1));
	    		}
	    		
	    	}
	    	catch(SQLException e){
	    		e.printStackTrace();
	    		return null;
	    	}
	    	return allAgents;
	 }
	 
	// Get a list of agencies
	public static ObservableList<String> getAgencies(){
		Connection conn = DBHelper.getConnection();
		ObservableList<String> agencies = FXCollections.observableArrayList();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT AgencyId FROM agencies");
			while(rs.next()){
				agencies.add(rs.getString(1));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return agencies;
	}

	// Get a list of agencies
	public static List<String> getAgentByUserName(String userName){
	 	List<String> userInfo = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		try{
			Statement stmt = conn.createStatement();
			String sql = "SELECT AgtLastName, AgtPassword FROM agents WHERE AgtEmail = '"+userName+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst() ) {
				userInfo.add("wrong");
				userInfo.add("wrong");

				// handle empty set: throw error or return
			} else {
				while (rs.next()) {
					userInfo.add(rs.getString(1));
					userInfo.add(rs.getString(2));
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return userInfo;
	}



	
	// Get a list of agencies
		public static ObservableList<String> getAllAgentIds(){
			Connection conn = DBHelper.getConnection();
			ObservableList<String> agentIds = FXCollections.observableArrayList();
			try{
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT AgentId FROM agents");
				while(rs.next()){
					agentIds.add(rs.getString(1));
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				return null;
			}
			return agentIds;
		}
	
	// Update Specific Agent with the given ID and information
	public static boolean updateAgent(
			String inputAgtId,
			String inputAgtFirstName,
			String inputAgtLastName,
			String inputAgtPhone,
			String inputAgtEmail,
			String inputAgtPosition,
			String inputAgtAgency) {
		
		boolean success = false;
		
		String sqlStatement = "UPDATE agents SET AgtFirstName='" + inputAgtFirstName
		+ "', AgtLastName='" + inputAgtLastName
		+ "', AgtBusPhone='" + inputAgtPhone
		+ "', AgtEmail='" + inputAgtEmail
		+ "', AgtPosition='" + inputAgtPosition
		+ "	', AgencyId=" + inputAgtAgency
		+ " WHERE AgentId=" + inputAgtId;
		
		try {
			Connection conn = DBHelper.getConnection();
			success = executeCommand(sqlStatement);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static boolean addAgent(
			String inputAgtFirstName,
			String inputAgtLastName,
			String inputAgtPhone,
			String inputAgtEmail,
			String inputAgtPosition,
			String inputAgtAgency
			) {
		boolean success = false;
		
		String sqlStatement = "INSERT INTO agents (AgtFirstName, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId) VALUES('" 
		+ inputAgtFirstName
		+ "', '" + inputAgtLastName
		+ "', '" + inputAgtPhone
		+ "', '" + inputAgtEmail
		+ "', '" + inputAgtPosition
		+ "', " + inputAgtAgency 
		+ ")";
		try {
			success = executeCommand(sqlStatement);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return success;
	}
	
	public static boolean deleteAgent(String inputAgtId) {
		boolean success = false;
		String sqlStatement = "DELETE FROM agents WHERE AgentId=" + inputAgtId;
		
		try {
			success = executeCommand(sqlStatement);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	
}
