package Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	private  Connection  con;
	

	   public  Doctor( Connection con) 
	   {
		this.con=con;
	
	    }
	   
	  
	   
	    public void viewDoctors() throws SQLException
	    {
	     String query ="select * from doctors";
	     PreparedStatement stmt= con.prepareStatement(query);
	     ResultSet rs=stmt.executeQuery();
	     System.out.println(" Doctor ");
	     System.out.println("+------------+-----------------+------------------------+");
	     System.out.println("|  DoctorID  |      Name       |     specilization      |");
	     System.out.println("+------------+-----------------+------------------------+");
	     while(rs.next())
	     {
	    	 int doctor_id=rs.getInt("id");
	    	 String name= rs.getString("name");
	    	 String specilization=rs.getString("specialization");
	    	 System.out.println("        "+ doctor_id +"     "+ "     " + name  +"    "+"          " +specilization+  "   ");
	    	 System.out.println("+------------+-----------------+--------------------------+");
	     }
	     
	    }//method close

	    
	    public boolean getDoctorsById(int id) throws SQLException
	    {
			String query="select * from Doctors where id= ?";
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
	    }//c
}
