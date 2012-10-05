import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Jdbcclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int no_of_rows = 0;
		Connection conn = null;
		String url = "jdbc:oracle:thin:vxm4668/pRucA9DHk@omega:1521:cse1";			
		String query1 = null;
		String query2 = null;
		String query3 = null;
		String query4 = null;
		String query5 = null;
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			 
	        conn = DriverManager.getConnection(url);
			}catch (SQLException e) {			// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
				
			}
			
			try {
				if(Integer.parseInt(args[0]) == 1)
				{
				 fstream = new FileInputStream("/home/v/vx/vxm4668/EMPLOYEE.txt");
				  // Get the object of DataInputStream
				   in = new DataInputStream(fstream);
				   br = new BufferedReader(new InputStreamReader(in));
				   String values;
				  //Read File Line By Line
				  while ((values = br.readLine()) != null)  
				  {  // Print the content on the console
				 				 
						query1 = "insert into Employee(Fname,minit,Lname,Ssn,Bdate,Address,Sex,Salary,Super_ssn,Dno) values("+values+")";
						PreparedStatement stmt = conn.prepareStatement(query1);
				        no_of_rows=stmt.executeUpdate();
				        query1 = null;
				        stmt.close();				        
				        
				  }
				    
				  if(no_of_rows > 0)
				  {
					  System.out.println("Values inserted from the file successfully");
				  }
				}
				if(Integer.parseInt(args[0]) == 2)
				{
				FileInputStream fstream1 = new FileInputStream("/home/v/vx/vxm4668/PROJECT.txt");
				  // Get the object of DataInputStream
				  DataInputStream in1 = new DataInputStream(fstream1);
				  BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
				   String values1;
				  //Read File Line By Line
				  while ((values1 = br1.readLine()) != null)  
				  {  // Print the content on the console
				 				 
						query2 = "insert into Project(Pname,Pnumber,Plocation,Dnum) values("+values1+")";
						PreparedStatement stmt = conn.prepareStatement(query2);
				        no_of_rows=stmt.executeUpdate();
				        query2 = null;
				        stmt.close();				        
				        
				  }
				    
				  if(no_of_rows > 0)
				  {
					  System.out.println("Values inserted from the file successfully");
				  }
				}
				if(Integer.parseInt(args[0]) == 3)
				{
				   fstream = new FileInputStream("/home/v/vx/vxm4668/WORKS_ON.txt");
				  // Get the object of DataInputStream
				   in = new DataInputStream(fstream);
				   br = new BufferedReader(new InputStreamReader(in));
				   String values;
				  //Read File Line By Line
				  while ((values = br.readLine()) != null)  
				  {  // Print the content on the console
				 				 
						query3 = "insert into Works_on(Essn,Pno,Hours) values("+values+")";
						PreparedStatement stmt = conn.prepareStatement(query3);
				        no_of_rows=stmt.executeUpdate();
				        query3 = null;
				        stmt.close();				        
				        
				  }
				    
				  if(no_of_rows > 0)
				  {
					  System.out.println("Values inserted from the file successfully");
				  }
				}
				if(Integer.parseInt(args[0]) == 4)
				{
				   fstream = new FileInputStream("/home/v/vx/vxm4668/DEPARTMENT.txt");
				  // Get the object of DataInputStream
				   in = new DataInputStream(fstream);
				   br = new BufferedReader(new InputStreamReader(in));
				   String values;
				  //Read File Line By Line
				  while ((values = br.readLine()) != null)  
				  {  // Print the content on the console
				 				 
						query4 = "insert into Department(Dname,Dnumber,Mgr_ssn,Mgr_start_date) values("+values+")";
						PreparedStatement stmt = conn.prepareStatement(query4);
				        no_of_rows=stmt.executeUpdate();
				        query4 = null;
				        stmt.close();				        
				        
				  }
				    
				  if(no_of_rows > 0)
				  {
					  System.out.println("Values inserted from the file successfully");
				  }
				}
				if(Integer.parseInt(args[0]) == 5)
				{
				   fstream = new FileInputStream("/home/v/vx/vxm4668/DEPT_LOCATIONS.txt");
				  // Get the object of DataInputStream
				   in = new DataInputStream(fstream);
				   br = new BufferedReader(new InputStreamReader(in));
				   String values;
				  //Read File Line By Line
				  while ((values = br.readLine()) != null)  
				  {  // Print the content on the console
				 				 
						query5 = "insert into Dept_locations(Dnumber,Dlocation) values("+values+")";
						PreparedStatement stmt = conn.prepareStatement(query4);
				        no_of_rows=stmt.executeUpdate();
				        query5 = null;
				        stmt.close();				        
				        
				  }
				    
				  if(no_of_rows > 0)
				  {
					  System.out.println("Values inserted from the file successfully");
				  }
				}
				
				     
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				
			}
			

	}

}

