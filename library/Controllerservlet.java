package com.controller;

import com.webapp.dbconnection.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

/**
 * Servlet implementation class Controllerservlet
 */
@WebServlet("/Controllerservlet")
public class Controllerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controllerservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside servlet");
		ResultSet rs = null;
        PrintWriter out = response.getWriter();
        ArrayList list = null;

		int ano = Integer.parseInt(request.getParameter("tno")); //get the unique activity identifier from the app
		//ano = 1;
		if(ano == 1) // existing user sign in
		{		
			System.out.println("inside servlet");
		    int uid = Integer.parseInt(request.getParameter("id"));
		    String password = request.getParameter("pass");
			String query = "select Fname from user where MID = ? and password = ?"; //query to identify authenticity of user
			// make jdbc call to mysql server
			Connection conn = new DBConnection().getDbconnection(); 
			try {
				PreparedStatement ps = conn.prepareStatement(query); //use prepared statement to make the query ready
				ps.setInt(1, uid);
				ps.setString(2,password);
				rs = ps.executeQuery(); // execute the query
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs.next()) // if entry is found in db, set the welcome message
				{
					System.out.println("db connected");
					String fname = rs.getString(1);
					out.println("Welcome"+""+fname+"");
				}
				else // else set a flag
				{
					out.println("N");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				//close existing database connections
				//try {
					//rs.close();
				
				/*if(conn != null)
				{
					//conn.close();
				}
				}// //catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}//*/
				
			}
		}
		
		if (ano == 2) // admin sign in
		{
			String uid = request.getParameter("id");
		    String password = request.getParameter("pass");
			String query = "select Fname from admin where AID = ? and password = ?";
			Connection conn = new DBConnection().getDbconnection();
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, uid);
				ps.setString(2,password);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs.next())
				{
					String fname = rs.getString(1);
					out.println("Welcome"+" "+fname+"");
				}
				else
				{
					out.println("N");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					rs.close();
				
				if(conn != null)
				{
					conn.close();
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		if(ano == 3) // new user sign up
		{
			String fname = request.getParameter("fullname");
			//String lname = request.getParameter("lastname");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String phoneno = request.getParameter("phno");
			String password = request.getParameter("password");
			//String confirmPassword = request.getParameter("confirmpassword");
			int no_of_rows = 0;
			ResultSet rs1 = null;
			
			
			
			String query = "insert into user(Fname,password,emailid,Maddress,Pno) values (?,?,?,?,?) ";
			Connection conn = new DBConnection().getDbconnection();
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1,fname);
				
				ps.setString(2,password);
				ps.setString(3,email);
				ps.setString(4,address);
				ps.setString(5,phoneno);
				no_of_rows =ps.executeUpdate();
				
				if(no_of_rows == 1) // if the insert is successfull generate an unique id to the user
				{
					PreparedStatement ps1 = null;
					String query1 = "select MID from user where Pno = ? ";
					Connection conn1 = new DBConnection().getDbconnection();
					 ps1 = conn.prepareStatement(query1);
					 ps1.setString(1,phoneno);
					
						 rs1 = ps1.executeQuery();
						if(rs1.next())
						{
							out.println("Thank you for registering.Your user id is"+rs1.getInt(1)+" Please login ");
						}
									
				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					rs1.close();
				
				if(conn != null)
				{
					conn.close();
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
			
			if (ano == 4) //search based on bookname
			{
				list = new ArrayList();
				String Keyword = request.getParameter("keyword");
				String searchquery = "select Bname from book where Bname like'%"+Keyword+"%' and Bnoofcopies > 0 " ;
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(searchquery);
					//ps.setString(1, uid);
					//ps.setString(1,Keyword);
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while(rs.next()) //add the books into the arraylist
					{
						list.add(rs.getString(1));
						//System.out.println(list.get(1));
					}
					out.println(list);
				}catch
					(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						rs.close();					
					if(conn != null)
					{
						conn.close();
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			if (ano == 5) //search based on books ISBN
			{
				list = new ArrayList();
				int Keyword = Integer.parseInt(request.getParameter("keyword"));
				String searchquery = "select Bname from book where BISBN = ? and Bnoofcopies > 0 ";
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(searchquery);
					//ps.setString(1, uid);
					ps.setInt(1,Keyword);
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while(rs.next())
					{
						list.add(rs.getString(1));
					}
					out.println(list);
				}catch
					(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						rs.close();					
					if(conn != null)
					{
						conn.close();
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
			
			if (ano == 6) //search based on book author
			{
				list = new ArrayList();
				String Keyword = request.getParameter("keyword");
				String searchquery = "select Bname from book where Bauthor like'%"+Keyword+"%'and Bnoofcopies > 0 ";
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(searchquery);
					//ps.setString(1, uid);
					//ps.setString(1,Keyword);
					rs = ps.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while(rs.next())
					{
						list.add(rs.getString(1));
					}
					out.println(list);
				}catch
					(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						rs.close();					
					if(conn != null)
					{
						conn.close();
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
				
			
			if(ano==8) // To borrow the book
			{
				
				String bname = request.getParameter("bname");
				String mid = request.getParameter("mid");
				int isbn = Integer.parseInt(request.getParameter("isbn"));
				String borrowQuery = " UPDATE book SET Bnoofcopies = Bnoofcopies-1 WHERE BISBN = ?" ;
				String borrowQuery1 = "INSERT INTO report(MID,Bisbn,Bdate,Ddate,IsReturn,fine,Bname) values (?,?,curdate(),DATE_ADD(‘curdate()’, INTERVAL 10 DAY),?)";
				
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(borrowQuery);
					PreparedStatement ps1 = conn.prepareStatement(borrowQuery1);
					
					ps.setInt(1, isbn);
					ps1.setString(1,mid);
					ps1.setInt(2, isbn);
					ps1.setString(5,bname);
				   int noOfRows=ps.executeUpdate();
				   int noOfRows1= ps1.executeUpdate();
				   
				   if(noOfRows ==1 && noOfRows1 ==1 )
				   {
					   out.println("Y");
				   }else
				   {
					   out.println("N");
				   }
				   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				finally
				{
					try {
						rs.close();					
					if(conn != null)
					{
						conn.close();
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		
				}
	

}

