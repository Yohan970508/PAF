/*
 * IT19142692
 * Anuththara K.G.S.N
 * */

package model;

import java.sql.*;

public class Project_Management {

	//getting mysql connection
	public Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/paf", "root", "");
			System.out.println("Successfully Connected");

		} catch (Exception e) {
			System.out.println("Not connected");
			e.printStackTrace();
			
		}
		return con;
	}
	
	//insert project details
	public String insertProject(int projectID,String projectName, String projectType, String projectDescription) {

		String output = ""; 
			
		try {
			Connection con = connect();

			if (con == null)

			{
				return "Error occurs connecting to the DB when inserting.";
			}
			// create a prepared statement
			String query = " insert into project(`projectID`,`projectName`,`projectType`,`projectDescription`)" + " values (?, ?, ?, ?)";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
				
			// binding values to project table
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, projectName);
			preparedStmt.setString(3, projectType);
			preparedStmt.setString(4, projectDescription);
			
			preparedStmt.execute();
			con.close();
			output = "Successfully Inserted."; 
				
			} 
			catch (Exception e) {
			output = "Error occurs inserting project details.";
			System.err.println(e.getMessage());
			}
			return output;

		}
	
	//read project details from the system
	public String readProject() {
		String output = "";
		try {

			Connection con = connect();

			if (con == null) {

				return "Error occurs connecting to the DB when reading";

			}
			//Table display
			output = "<table border=\"1\"><tr>" + "<th>Project ID</th>" + "<th>Project Name</th>" + "<th>Project Type</th>" + "<th>Project Description</th></tr>";

			String query = "select * from project";

			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				
				String projectID = Integer.toString(rs.getInt("projectID"));
				System.out.println(projectID);
				
				String projectName = rs.getString("projectName");
				String projectType = rs.getString("projectType");
				String projectDescription = rs.getString("projectDescription");

				//Add to the table
				output += "<td>" + projectID  + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + projectType + "</td>";
				output += "<td>" + projectDescription + "</td>";

			}

			con.close();

			output += "</table>";

		} catch (Exception e) {

			output = "Error occurs reading project details.";
			System.err.println(e.getMessage());

		}

		return output;

	}
	
	//update existing project details
	public String updateProject(String projectID, String projectName, String projectType, String projectDescription)
	{
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error occurs connecting to the DB when updating";
			}

			// create a prepared statement
			
			String query = "UPDATE project SET projectName=?,projectType=?,projectDescription=? WHERE projectID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values to query
			
			preparedStmt.setString(1, projectName);
			preparedStmt.setString(2, projectType);
			preparedStmt.setString(3, projectDescription);
			preparedStmt.setString(4, projectID);

			// Executeing the statement
			 preparedStmt.execute(); 
			 con.close();

			output = "Successfully updated project details.";
		
		}

		catch (Exception e) {

			output = "Error occurs updating project details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete project details
    public String deleteProject(String projectID) {
		
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error occurs connecting to the DB when deleting.";
			}

			// create a prepared statement
			String query = "delete from project where projectID=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, projectID);

			// executing the statements
			preparedStmt.execute();

			con.close();

			output = "Successfully deleted project details.";
			
		} catch (Exception e) {

			output = "Error occurs deleting project details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

