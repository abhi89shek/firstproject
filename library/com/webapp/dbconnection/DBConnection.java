package com.webapp.dbconnection;

import java.util.*;
import java.sql.*;

public class DBConnection {

	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/test";
	String username = "root";
	String password = "password";
	
	public Connection getDbconnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 
        conn = DriverManager.getConnection(url,username,password);
		}catch (SQLException e) {			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
			
		}
		return conn;
		  
	}
	
	
}
