package com.ax.mysqllearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://192.168.0.122:3306/test_platform"; 
	static final String USER = "root";
	static final String PASS = "root";
	 
	
    public void testDB()
    {
      
    	 Connection conn = null;
         Statement stmt = null;
         try{
        	 Class.forName(JDBC_DRIVER);
        	 print("connect...");
        	 conn = DriverManager.getConnection(DB_URL,USER,PASS);
        	 
        	 stmt = conn.createStatement();
        	 String sql = "show tables";
        	 ResultSet rs = stmt.executeQuery(sql);
        	 ResultSetMetaData metaData = rs.getMetaData();
        	 int columnCount = metaData.getColumnCount();
        	 
        	 
        	 String s  = metaData.getColumnLabel(columnCount);
        	 
        	 print(s);
        	 s  = metaData.getColumnName(columnCount);
        	 print(s);
        	 s = metaData.getColumnClassName(columnCount);
        	 print(s);
        	 
        	 
        	 while(rs.next()) {
        		
        		
        		 print(rs.getString("Tables_in_test_platform"));
        		 
        	 }
        	 rs.close();
        	 stmt.close();
        	 conn.close();
        	 
        	 
         }catch(Exception e) {
        	 e.printStackTrace();
         }
    }
    
    public void print(Object msg) {
    	System.out.println(msg);
    }
    
}
