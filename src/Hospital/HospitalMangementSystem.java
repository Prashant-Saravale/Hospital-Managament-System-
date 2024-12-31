package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class HospitalMangementSystem {
	
	//private static final Connection con;

	Connection con;
	
	public static void main(String args[]) throws SQLException
	{
		 
			
			   DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			   java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");	
		      
		      
		  Scanner sc= new Scanner(System.in);
		  Patients pt = new Patients(con, sc);
		  Doctor dt= new Doctor(con);
		  while(true)
		  { 
			  System.out.println("Hospital Management System...");
			  System.out.println(" 1. AddPatients");
			  System.out.println(" 2. View Patients");
			  System.out.println(" 3. view Doctor");
			  System.out.println(" 4. Book Appnoiment");
			  System.out.println(" 5. Exit");
			   
			  System.out.println("Enter the your choice::");
			  int chioce=sc.nextInt();
			  switch(chioce)
			  {
			  case 1:
				  //addPatients
				  pt.addpatients();
				  System.out.println();
				   break;
				
			  case 2:
				  //viewpatient
				  pt.viewpatients();
				  System.out.println();
				   break;
			  case 3:
				  //view Doctore
				  dt.viewDoctors();
				  System.out.println();
				   break;
			  case 4:
				  //Book appoinment
				  appointment(pt, dt, con, sc);
				  System.out.println();
				   break;
			  case 5:
				  System.out.println("Nice to you meet..");
				  
				  break;
				
				  
			 default :
				 System.out.println("Enter valid choice");
				 break;
				 
			 }//switch
		  
		  }//while		
	}//main
	
	 public static void appointment(Patients pt,Doctor dt,Connection con, Scanner sc) throws SQLException
	 {
		 System.out.println("Enter Patient Id: ");
		  int patient_id= sc.nextInt();
		  System.out.println("Enter Doctor Id: ");
		  int doctor_id=sc.nextInt();
		  System.out.println("Enter Appointment Date(yyyy-mm-dd)");
		   String appointment_date=sc.next();
	 
	     if(pt.getPatientsById(doctor_id)&& dt.getDoctorsById(doctor_id))
	     {
	    	  
	    	 if(checkDoctorAvilablity(doctor_id,appointment_date))
	    	 { 
	    		 String query="insert into  appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
	    		 PreparedStatement stmt=con.prepareStatement(query);
	    		 stmt.setInt(1, patient_id);
	    		 stmt.setInt(2, doctor_id);
	    		 stmt.setString(3,appointment_date);
	             int i= stmt.executeUpdate();
	             if(i>0)
	             {
	            	 System.out.println("Appointment Booked..");
	             }
	             else
	             {
	            	 System.out.println(" Failed to Book Appointment.");
	             }
	    	 }
	    	 else
	    	 {
	    		 System.out.println("Doctor is not Avilable...");
	    	 }
	    	 
	     }
	     else
	     {
	    	 System.out.println("Patients and Doctor not Exits...");
	     }
		   
	 
	 }

	private static boolean checkDoctorAvilablity(int doctor_id, String appointment_date) throws SQLException 
	{
		 java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");	
	      
		// TODO Auto-generated method stub
		String query= "select count(*)from appointments where doctor_id= ? and appointment_date = ?";
		 PreparedStatement stmt=con.prepareStatement(query);
          stmt.setInt(1, doctor_id);
          stmt.setString(2,appointment_date);
          ResultSet rs= stmt.executeQuery();
          if(rs.next())
          {
        	  int count= rs.getInt(1);
        	  if(count==0)
        	  {
        		  return true;
        	  }
        	  else
        	  {
        		  return false;
        	  }
          }return false;
          
	}
	
	 
}//class close
