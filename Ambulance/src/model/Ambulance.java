package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ambulance {
	
private Connection connect()  {   
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aaaa", "root", "");   
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public String insertAmbulanceDetails(String driverCode, String driverName, String mobileNo, String vehicleNo) 
	{   
		String output = ""; 
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for inserting."; } 
	 
	      String query = " insert into ambu (`driverID`,`driverCode`,`driverName`,`mobileNo`,`vehicleNo`)"     
	    		  + " values (?, ?, ?, ?, ?)"; 
	 
	      PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      preparedStmt.setInt(1, 0);   
	      preparedStmt.setString(2, driverCode);   
	      preparedStmt.setString(3, driverName);    
	      preparedStmt.setString(4, mobileNo);   
	      preparedStmt.setString(5, vehicleNo); 
	 
	   
	      preparedStmt.execute();    
	      con.close(); 
	 
	   output = "Inserted successfully";   
	   
	  }catch (Exception e)   
	  	
	 	{    
		   output = "Error while inserting the Details";    
		   System.err.println(e.getMessage());   
		} 
	 return output;  
	}
	
	
public String readAmbulanceDetails()  
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
			
	 
	     output = "<table border=\"1\"><tr><th>Driver Code</th><th>Driver Name</th><th>Mobile No</th><th>Vehicle No</th><th>Update</th><th>Remove</th></tr>"; 
	 
	   
	     String query = "select * from ambu";    
	     Statement stmt = con.createStatement();    
	     ResultSet rs = stmt.executeQuery(query); 
	 
	     while (rs.next())    
	     {     
	    	 String driverID = Integer.toString(rs.getInt("driverID"));     
	    	 String driverCode = rs.getString("driverCode");     
	    	 String driverName = rs.getString("driverName");     
	    	 String mobileNo =rs.getString(("mobileNo"));     
	    	 String vehicleNo = rs.getString("vehicleNo"); 
	     
	 
	         output += "<tr><td>" + driverCode + "</td>";     
	         output += "<td>" + driverName + "</td>";     
	         output += "<td>" + mobileNo + "</td>";     
	         output += "<td>" + vehicleNo + "</td>"; 
	 
	         output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"      
	         				+ "<td><form method=\"post\" action=\"items.jsp\">"      
	         				+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"      
	         				+ "<input name=\"driverID\" type=\"hidden\" value=\"" + driverID     
	         				+ "\">" + "</form></td></tr>";    
	         } 
	
	     con.close(); 
	 
	     output += "</table>";   
	     }   
		catch (Exception e)  
		{    
			output = "Error while reading the Details.";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	  } 
	
	public String updateAmbulanceDetails(String ID,String driverCode, String driverName, String mobileNo, String vehicleNo)  
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return "Error while connecting to the database for updating."; 
				} 
	 
	   String query = "UPDATE ambu SET driverCode=?,driverName=?,mobileNo=?,vehicleNo=? WHERE driverID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setString(1, driverCode);    
	   preparedStmt.setString(2, driverName);   
	   preparedStmt.setDouble(3, Integer.parseInt(mobileNo));    
	   preparedStmt.setString(4, vehicleNo);    
	   preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   output = "Updated successfully";   
	}   
		catch (Exception e)   
	{    
			output = "Error while updating the Details.";    
			System.err.println(e.getMessage());   
			
	} 
	 
	  return output;  
	  
	}
	
	public String deleteAmbulanceDetails(String driverID)  
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for deleting."; } 
	 
	   String query = "delete from ambu where driverID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setInt(1, Integer.parseInt(driverID)); 
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Deleted successfully"; 
	   
	  }   
	  catch (Exception e)   
	  {    
		  output = "Error while deleting the Details.";    
		  System.err.println(e.getMessage());   } 
	 
	  return output;  }
	
	
}
