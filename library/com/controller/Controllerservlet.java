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
		ResultSet rs = null;
        PrintWriter out = response.getWriter();

		int ano = Integer.parseInt(request.getParameter("activityno"));
		if(ano == 1)
		{			
		    String uid = request.getParameter("userid");
		    String password = request.getParameter("password");
			String query = "select Fname from user where MID = ? and password = ?";
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
					out.println("Welcome"+""+fname+"");
				}
				else
				{
					out.println("invalid username/password");
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
		
		if (ano == 2)
		{
			String uid = request.getParameter("userid");
		    String password = request.getParameter("password");
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
					out.println("Welcome"+""+fname+"");
				}
				else
				{
					out.println("invalid username/password");
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
		if(ano == 3)
		{
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String phoneno = request.getParameter("phno");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmpassword");
			int no_of_rows = 0;
			
			if (!(password.equals(confirmPassword)))
			{
				out.println("please confirm the password. Does not match");
				return ;
			}
			if(!(email.contains("@")))
			{
				out.println("not a valid email id");
				return ;
			}
			String query = "insert into user(fname,lname,password,email,address,phno) values (?,?,?,?,?,?) ";
			Connection conn = new DBConnection().getDbconnection();
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1,fname);
				ps.setString(2,lname);
				ps.setString(3,password);
				ps.setString(4,email);
				ps.setString(5,address);
				ps.setString(6,phoneno);
				no_of_rows =ps.executeUpdate();
			
				if(no_of_rows == 1)
				{
					
					out.println("Thank you for registering. Please login ");
				}
				else
				{
					out.println("error in updating details.Please contact admin");
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
