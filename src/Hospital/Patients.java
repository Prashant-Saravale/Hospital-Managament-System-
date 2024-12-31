package Hospital;

import java.sql.*;
import java.util.Scanner;

public class Patients 
{

private  Connection  con;
private Scanner sc;

   public  Patients( Connection con,Scanner sc) 
   {
	this.con=con;
	this.sc=sc;
    }
   
   public void addpatients() throws SQLException 
   {
	   
	   System.out.println("Enter the Patients Name::");
	   String name=sc.next();
	   System.out.println("Enter the Patients Age::"); 
	   int age=sc.nextInt();
	   System.out.println("Enter the Patients Gender::");
	   String gender=sc.next();
	   String query="insert into patients(name,age,gender) values(?,?,?)";
	   PreparedStatement stmt= con.prepareStatement(query);
	   stmt.setString(1,name);
	   stmt.setInt(2, age);
	   stmt.setString(3, gender);
	   int i= stmt.executeUpdate();
	   
	  if(i>0)
	  {
		  System.out.println("patients added succesfull");
	  }
	  else
	  {
		  System.out.println("Failed to added patients.");
	  }
	   
    }//close method
   
    public void viewpatients() throws SQLException
    {
     String query ="select * from patients";
     PreparedStatement stmt= con.prepareStatement(query);
     ResultSet rs=stmt.executeQuery();
     System.out.println(" Patients ");
     System.out.println("+------------+-----------------+--------------+-----------------+");
     System.out.println("|  ParentID  |      Name       |     Age      |      Gender     |");
     System.out.println("+------------+-----------------+--------------+-----------------+");
     while(rs.next())
     {
    	 int id=rs.getInt("id");
    	 String name= rs.getString("name");
    	 int age=rs.getInt("age");
    	 String gender=rs.getString("gender");
    	// System.out.printf("|%-10s|%-11s|%c-8|%-9s|\n",id,name,age,gender);
    	 System.out.println("         "+ id +"     "+ "     " + name +"    "+"           " +age+  "   "+"         "+ gender +"   ");
    	 System.out.println("+-------------+----------------+---------------+----------------+");
     }
     
    }//method close

    
    public boolean getPatientsById(int id) throws SQLException
    {
		String query="select * from patients where id= ?";
		PreparedStatement stmt=con.prepareStatement(query);
		stmt.setInt(1,id);
		ResultSet rs=stmt.executeQuery();
		if(rs.next())
		{ 
			return true;
		}
		else
		{
			return false;
	
		}
    }//close method 
}//close class 
