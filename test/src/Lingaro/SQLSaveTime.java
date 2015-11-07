package Lingaro;

import java.sql.*;


public class SQLSaveTime {
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";

	   
	   static final String USER = "username";
	   static final String PASS = "password";
	   
	   public void Start (String TestName, long TestTime) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	         
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE TABLE TIMETEST " +
                  "(id INTEGER not NULL, " +
                  " name VARCHAR(255), " + 
                  " time BIGINT, " +  
                  " PRIMARY KEY ( id ))"; 

     stmt.executeUpdate(sql);
     System.out.println("Created table in given database...");

	   }catch(SQLException se){
	     
	      se.printStackTrace();
	   }catch(Exception e){
	      
	      e.printStackTrace();
	   }finally{
	      
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
	}
}

