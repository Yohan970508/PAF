package model;
import java.sql.*; 

public class Products {

	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productmanagement", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 }

	public String insertProduct(String pro_id, String name, String description, String type, String location) 
	 { 
	 String output = ""; 
	 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 
	 // create a prepared statement
	 String query = " insert into products(`pro_id`,`name`,`description`,`type`,`location`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0);  
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, description); 
	 preparedStmt.setString(4, type); 
	 preparedStmt.setString(5,location);
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the product."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String readProcut() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>pro_id</th><th> Name</th>" +
	 "<th>description</th>" + 
	 "<th>type</th>" +
	 "<th>location</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from products"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String pro_id = Integer.toString(rs.getInt("pro_id")); 
	 String name = rs.getString("name"); 
	 String description = rs.getString("description"); 
	 String type = rs.getString("type"); 
	 String location = rs.getString("location"); 
	 // Add into the html table
	 output += "<tr><td>" + pro_id + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + description + "</td>"; 
	 output += "<td>" + type + "</td>"; 
	 output += "<td>" + location + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='pro id' type='hidden' value='" + pro_id + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the products."; 
	 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 } 

	public String updateProduct(String pro_id , String name, String description, String type, String location)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE products SET name=?,description=?,type=?,location=?, WHERE pro_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		    
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, description); 
		 preparedStmt.setString(3, type); 
		 preparedStmt.setString(4, location);
		 preparedStmt.setInt(5, Integer.parseInt(pro_id));
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		} 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the product."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		public String deleteProduct(String pro_id) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from products where pro_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(pro_id)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the product."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }  
}
