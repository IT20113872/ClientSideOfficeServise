package model;

import util.DB_Connector;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class PowerSource {
	
	private static Connection con = null;
	
	//insert 
	public String insertPowerSource(String OfficeID, String OfficeName, String OfficeType, String Address, String phone, String manager) {
		String output = "";
		
		try {
				con = DB_Connector.connect();
			
			if(con == null)
				return "Database connection failed for inserting data";
			
			// create a prepared statement
			String query = " insert into newClientDB (`ID`,`OfficeID`,`OfficeName`,`OfficeType`,`Address`,`phone`,`manager`)"
					 + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,OfficeID);
			preparedStmt.setString(3,OfficeName);
			preparedStmt.setString(4,OfficeType);
			preparedStmt.setString(5,Address);
			preparedStmt.setString(6,phone);
			preparedStmt.setString(7,manager);
			
			
			// execute the statement
			preparedStmt.executeUpdate();
			con.close();
			String newClientDB = readPowerSource();
			output = "{\"status\":\"success\", \"data\": \"" + newClientDB + "\"}";
		}
		
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the power source.\"}";
			System.err.println(e.getMessage());
			
		}
		
		return output;
	}
	
	//Read all the power sources
	public String readPowerSource()
	{
		String output = "";
		try
		{
			con = DB_Connector.connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>OfficeID</th> <th>OfficeName</th><th>OfficeType</th><th>Address</th><th>Phone Number</th><th>Manager ID</th>"
			+"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from newClientDB";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			//insert into newClientDB (`ID`,`OfficeID`,`OfficeName`,`OfficeType`,`Address`,`phone`,`manager`)"
			while (rs.next())
			{
				String ID = Integer.toString(rs.getInt("ID"));
				String OfficeID = rs.getString("OfficeID");
				String OfficeName = rs.getString("OfficeName");
				String OfficeType = rs.getString("OfficeType");
				String Address = rs.getString("Address");
				String phone = rs.getString("phone");
				String manager = rs.getString("manager");
				
				// Add into the html table
				output += "<tr><td><input id='hidPowerSourceIDUpdate' name='hidPowerSourceIDUpdate' type='hidden' value='" 
				+ ID + "'>" + OfficeID + "</td>";
				output += "<td>" + OfficeName + "</td>";
				output += "<td>" + OfficeType + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + manager + "</td>";

				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td>"
				+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-powersource='"+ID+"'></td></tr>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		
			catch (Exception e)
			{
				output = "Error while reading the power source details.";
				System.err.println(e.getMessage());
			}
			return output;
	}
	
	//Update
	//= " insert into newClientDB (`ID`,`OfficeID`,`OfficeName`,`OfficeType`,`Address`,`phone`,`manager`)"
	public String updatePowerSource(String ID, String OfficeID, String OfficeName, String OfficeType, String Address, String phone, String manager)
	{
		String output = "";
		
		try
		{
			con = DB_Connector.connect();
			if (con == null)
				return "Database connection failed for updating data."; 
			
			// create a prepared statement
			String query = "UPDATE newClientDB SET OfficeID=?,OfficeName=?,OfficeType=?,Address=?,phone=?,manager=? WHERE ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, OfficeID);
			preparedStmt.setString(2, OfficeName);
			preparedStmt.setString(3, OfficeType );
			preparedStmt.setString(4, Address);
			preparedStmt.setString(5,phone);
			preparedStmt.setString(6, manager);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newClientDB = readPowerSource();
			output = "{\"status\":\"success\", \"data\": \"" + newClientDB + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the power source.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Delete
	public String deletePowerSource(String ID)
	{
		String output = "";
		
		try
		{
			con = DB_Connector.connect();
			if (con == null)
				return "Database connection failed for deleting data."; 
			
			// create a prepared statement
			
		String query = "delete from newClientDB where ID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(ID));
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newClientDB = readPowerSource();
		output = "{\"status\":\"success\", \"data\": \"" + newClientDB + "\"}";
	}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the power source.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	/**Reading Source by the Employee Id**/
	public JsonObject readSourceEmp(String id)
	{
		JsonObject output = null;
		
		try
		{
			con = DB_Connector.connect();
			if (con == null) {
				output=new JsonObject();
				output.addProperty("MESSAGE", "Database connection failed for reading data.");
				//return "Database connection failed for reading data.";
			}
			//
			String query = "select * from newClientDB where Head_Engineer='"+id+"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				JsonObject dbObject = new JsonObject();
				dbObject.addProperty("name", rs.getString("Name"));
				dbObject.addProperty("address", rs.getString("Address"));
				dbObject.addProperty("province", rs.getString("Province"));
				dbObject.addProperty("type", rs.getString("Type"));
				dbObject.addProperty("power", rs.getString("PowerGenerated"));
				dbObject.addProperty("maintenanceDay", rs.getString("Address"));
				output=dbObject;
				
			}
			con.close();
			
		}
		catch (Exception e)
		{
			output=new JsonObject();
			output.addProperty("MESSAGE","Error while reading the power source details.");
			//output = "Error while reading the power source details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
