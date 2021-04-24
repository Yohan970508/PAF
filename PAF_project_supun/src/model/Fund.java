package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	
	//A common method to connect to the DB
	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	//Provide the correct details: DBServer/DBName, username, password
	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "abc123");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}
	public String insertFund(String projectname , String amount, String description)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into funddetails (`FundID`,`ProName`,`Amount`,`Descrip`)"
	+ " values (?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, projectname);
	preparedStmt.setDouble(3, Double.parseDouble(amount));
	preparedStmt.setString(4, description);
	
	// execute the statement3
	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the Fund.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String readFunds()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Fund ID</th><th>Project Name</th>" +
	"<th>Amount</th>" +
	"<th>Description</th>" +
	"<th>Update</th><th>Remove</th></tr>";
	String query = "select * from funddetails";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String FundID = Integer.toString(rs.getInt("FundID"));
	String ProName = rs.getString("ProName");
	String Amount = Double.toString(rs.getDouble("Amount"));
	String Descrip = rs.getString("Descrip");
	
	// Add into the html table
	
	output += "<tr><td>"+ FundID + "</td>";
	output += "<td>" + ProName + "</td>";
	output += "<td>" + Amount + "</td>";
	output += "<td>" + Descrip + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	+ "<td><form method='post' action='funds.jsp'>"
	+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	+ "<input name='FundID' type='hidden' value='" + FundID
	+ "'>" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the funds.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String updateFund(String ID, String projectname , String amount, String description)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE funddetails SET ProName=?,Amount=?,Descrip=? WHERE FundID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	
	preparedStmt.setString(1, projectname);
	preparedStmt.setDouble(2, Double.parseDouble(amount));
	preparedStmt.setString(3, description);
	preparedStmt.setInt(4, Integer.parseInt(ID));
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String deleteFund(String FundID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	
	if (con == null)
	{return "Error while connecting to the DB for deleting."; }
	
	// create a prepared statement
	String query = "DELETE from funddetails where FundID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(FundID));

	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully..";
	
	}
	catch (Exception e)
	{
		output = "Error while deleting the fund.";
		System.err.println(e.getMessage());
	}
	return output;
	}

}
