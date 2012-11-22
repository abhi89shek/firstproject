package com.controller;

import com.dbconnection.*;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Servlet implementation class Controllerservlet
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
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
		String str = "";
		String finalstr = null;
		String notiStr = "";
		
        PrintWriter out = response.getWriter();
        ArrayList<String> list = null;

		int activityNo = Integer.parseInt(request.getParameter("activityNo")); //get the unique activity identifier from the app
		//ano = 1;
		if(activityNo == 1) // existing user sign in
		{		
			System.out.println("inside servlet");
		    String uid = request.getParameter("userId");
		    String password = request.getParameter("password");
			String query = "select name from userdetails where username = ? and password = ?"; //query to identify authenticity of user
			// make jdbc call to mysql server
			Connection conn = new DatabaseConn().getDbconnection(); 
			try {
				PreparedStatement ps = conn.prepareStatement(query); //use prepared statement to make the query ready
				ps.setString(1, uid);
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
					out.println("Welcome ,"+""+fname+"");
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
		
		if(activityNo == 2) // new user sign up
		{
			String fname = request.getParameter("fullName");
			//String lname = request.getParameter("lastname");
			String username = request.getParameter("userId");			
			String password = request.getParameter("password");
			//String confirmPassword = request.getParameter("confirmpassword");
			int no_of_rows = 0;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			String exsQuery = "select 1 from userdetails where username = ?";	
			
			String query = "insert into userdetails(name,username,password) values (?,?,?) ";
			Connection conn = new DatabaseConn().getDbconnection();
			try {
				PreparedStatement ps1 = conn.prepareStatement(exsQuery);
				PreparedStatement ps = conn.prepareStatement(query);
				ps1.setString(1, username);
				ps.setString(1,fname);
				ps.setString(2,username);
				ps.setString(3,password);				
				rs2 = ps1.executeQuery();
				if(!(rs2.next()))
				{
				no_of_rows =ps.executeUpdate();
				}
				else
				{
					out.println("The username you have choosen already exists.Please choose a different one ");
				}
				
				if(no_of_rows == 1) // if the insert is successfull a success maessage is didplayed
				{
					PreparedStatement ps2 = null;
					String query1 = "select 1 from userdetails where username = ? ";
					Connection conn1 = new DatabaseConn().getDbconnection();
					 ps2 = conn.prepareStatement(query1);
					 ps2.setString(1,username);
					
						 rs1 = ps1.executeQuery();
						if(rs1.next())
						{
							out.println("Thank you for registering.Please login ");
						}
									
				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					if(rs != null)
					{
					rs.close();	
					}
				
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
		if(activityNo == 3)
		{
			String username = request.getParameter("userName");
			String notificationQuery = "select 1 from textuser where shareWithName = ? and seenFlag = 'N'";
			PreparedStatement ps3 = null;
			Connection conn = new DatabaseConn().getDbconnection();
			try {
				ps3 = conn.prepareStatement(notificationQuery);
				ResultSet notiResult = ps3.executeQuery();
				if(notiResult.next())
				{
					out.println("Hi! You have notifications pending.Please check");
				}
				else
				{
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(activityNo == 4)
		{
			String username = request.getParameter("userName");
			String notificationResult = "select senderName,event,sendToName,Sdate from textuser where shareWithName = ? and seenFlag = 'N'";
			PreparedStatement ps4 = null;
			PreparedStatement updateStat = null;
			Connection conn = new DatabaseConn().getDbconnection();
			try {
				ps4 = conn.prepareStatement(notificationResult);
				ResultSet notiResults = ps4.executeQuery();
				while(notiResults.next())
				{
					notiStr = notiStr + notiResults.getString(1)+"wants you to attend an event: "+notiResults.getString(2)+" of "+notiResults.getString(3)+" on "+notiResults.getDate(4).toString() + ":";
				}
				String finalNotiStr = notiStr.substring(0, (notiStr.length()-1));				
				String updateFlag = "update textuser set seenFlag = 'Y' where seenFLag = 'N' and event ="+notiResults.getString(2)+" and senderName ="+notiResults.getString(1)+" and Sdate ="+notiResults.getDate(4).toString() +"";
				updateStat = conn.prepareStatement(updateFlag);
				int no_of_rows = updateStat.executeUpdate();
				out.println(finalNotiStr);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(activityNo == 5)
		{
			String username = request.getParameter("myUserName");
			String sendTo = request.getParameter("contactName");
			String sdate = request.getParameter("date");
			String shareWith = request.getParameter("Sharewith");
			String event = request.getParameter("event");
			
			
			String insertShare = "insert into textuser (senderName,sendToName,shareWithName,event,Sdate,seenFlag) values (?,?,?,?,?,'N')";
			;
			PreparedStatement insertStat = null;
			try {
			insertStat.setString(1, username);
			insertStat.setString(2, sendTo);
			insertStat.setString(3, shareWith);
			insertStat.setString(4, event);
			insertStat.setString(5, sdate);

			
			Connection conn = new DatabaseConn().getDbconnection();
			
				insertStat = conn.prepareStatement(insertShare);
				int no_of_rows = insertStat.executeUpdate();		
				
				out.println("Y");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
