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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
		String str = "";
		String finalstr = null;
		
        PrintWriter out = response.getWriter();
        ArrayList<String> list = null;

		int ano = Integer.parseInt(request.getParameter("tno")); //get the unique activity identifier from the app
		//ano = 1;
		if(ano == 1) // existing user sign in
		{		
			System.out.println("inside servlet");
		    String uid = request.getParameter("id");
		    String password = request.getParameter("pass");
			String query = "select Fname from user where username = ? and password = ?"; //query to identify authenticity of user
			// make jdbc call to mysql server
			Connection conn = new DBConnection().getDbconnection(); 
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
		if(ano == 3) // new user sign up
		{
			String fname = request.getParameter("fullname");
			//String lname = request.getParameter("lastname");
			String username = request.getParameter("username");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String phoneno = request.getParameter("phno");
			String password = request.getParameter("password");
			//String confirmPassword = request.getParameter("confirmpassword");
			int no_of_rows = 0;
			ResultSet rs1 = null;
			ResultSet rs2 = null;
			
			String exsQuery = "select 1 from user where username = ?";	
			
			String query = "insert into user(Fname,username,password,emailid,Maddress,Pno) values (?,?,?,?,?,?) ";
			Connection conn = new DBConnection().getDbconnection();
			try {
				PreparedStatement ps1 = conn.prepareStatement(query);
				PreparedStatement ps = conn.prepareStatement(query);
				ps1.setString(1, username);
				ps.setString(1,fname);
				ps.setString(2,username);
				ps.setString(3,password);
				ps.setString(4,email);
				ps.setString(5,address);
				ps.setString(6,phoneno);
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
					String query1 = "select MID from user where username = ? ";
					Connection conn1 = new DBConnection().getDbconnection();
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
			
			if (ano == 4) //search based on bookname
			{
				list = new ArrayList<String>();
				String Keyword = request.getParameter("keyword");
				String searchquery = "select Bname,BISBN from book where Bname like'%"+Keyword+"%'" ;
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
					if(rs!= null)
					{
						while(rs.next()) //add the books into the arraylist
						{
							Integer isbn = rs.getInt(2);
							String bisbn = isbn.toString();
							str = str + rs.getString(1) + "("+ bisbn + ")" + ":";
						}
						finalstr = str.substring(0,(str.length()-1) );
						System.out.println(finalstr);
						out.println(finalstr);
					}
					else
					{
						out.println("N");
					}
				}catch
					(Exception e) {
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
			if (ano == 5) //search based on books ISBN
			{
				list = new ArrayList<String>();
				int Keyword = Integer.parseInt(request.getParameter("keyword"));
				String searchquery = "select Bname,BISBN from book where BISBN = ?";
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
					if(rs!= null)
					{
						while(rs.next()) //add the books into the arraylist
						{
							Integer isbn = rs.getInt(2);
							String bisbn = isbn.toString();
							str = str + rs.getString(1) + "("+ bisbn + ")" + ":";
						}
						finalstr = str.substring(0,(str.length()-1) );
						System.out.println(finalstr);
						out.println(finalstr);
					}
					else
					{
						out.println("N");
					}
				}catch
					(Exception e) {
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
			
			
			if (ano == 6) //search based on book author
			{
				list = new ArrayList<String>();
				String Keyword = request.getParameter("keyword");
				String searchquery = "select Bname,BISBN from book where Bauthor like'%"+Keyword+"%'";
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
					if(rs!= null)
					{
						while(rs.next()) //add the books into the arraylist
						{
							//list.add(rs.getString(1));
							Integer isbn = rs.getInt(2);
							String bisbn = isbn.toString();
							str = str + rs.getString(1) + "("+ bisbn + ")" + ":";
							//System.out.println(list.get(1));
						}
						finalstr = str.substring(0,(str.length()-1) );
						System.out.println(finalstr);
						out.println(finalstr);
					}
					else
					{
						out.println("N");
					}
				}catch
					(Exception e) {
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
			if (ano == 7) 
			{
				
				String sessionid = request.getParameter("sessionid");
				String reportquery = "select datediff(Ddate,curdate()) as diffdate ,Bname,Ddate,fine from report where MID = ? ";
				
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(reportquery);
					
					ps.setString(1, sessionid);
					//ps.setString(1,Keyword);
					rs = ps.executeQuery();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while(rs.next())
					{
						if (rs.getInt(1) == 1)
						{
								   out.println("Hello! your book "+rs.getString(2)+" is due tomorrow");
								   System.out.println("gotcha");
						}
						if (rs.getInt(1) == 0)
						{
						   out.println("Hello! your book "+rs.getString(2)+" is due today");
						   System.out.println("gotcha");
						}
						if (rs.getInt(1) < 0)
						{
							out.println("hello! your book "+rs.getString(2)+" was due on "+rs.getDate(3)+ ". you have been charged a fine of "+rs.getInt(4)+ " ");
						}
					}
					
				}catch
					(Exception e) {
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
			if(ano==8) // To borrow the book
			{
				
				String bname = request.getParameter("bname");
				String mid = request.getParameter("mid");
				int isbn = Integer.parseInt(request.getParameter("isbn"));
				String borrowQuery = " UPDATE book SET Bnoofcopies = Bnoofcopies-1 WHERE BISBN = ?" ;
				String borrowQuery1 = "INSERT INTO report(MID,Bisbn,Bdate,Ddate,IsReturned,fine,Bname) values ((select MID from user where username =?),?,curdate(),(DATE_ADD(curdate(), INTERVAL 10 DAY)),'N',0,?)";
				String borrowQuery2 = "select Bnoofcopies from book where BISBN = ? ";
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(borrowQuery);
					PreparedStatement ps1 = conn.prepareStatement(borrowQuery1);
					PreparedStatement ps3 = conn.prepareStatement(borrowQuery2);
					
					ps.setInt(1, isbn);
					ps1.setString(1,mid);
					ps1.setInt(2, isbn);
					ps1.setString(3,bname);
					ps3.setInt(1, isbn);
					ResultSet res = ps3.executeQuery();
					if(res.next())
					{
					 if(res.getInt(1) > 0)
					 {
					   int noOfRows=ps.executeUpdate();
					   int noOfRows1= ps1.executeUpdate();
					   
					   if(noOfRows == 1 && noOfRows1 == 1 )
					   {
						   System.out.println("ddate");
						   String ddatequery = "select Ddate from report where MID = (select MID from user where username = ?) and BISBN = ? ";
						   PreparedStatement ps2 = conn.prepareStatement(ddatequery);
						   ps2.setString(1, mid);
						   ps2.setInt(2, isbn);
						   rs = ps2.executeQuery();
						   if(rs.next())
						   {
							   System.out.println("success");
							   String duedate = rs.getDate(1).toString();
							   out.println(duedate);
						   }
						   
					   }else
					   {
						   out.println("");
					   }
					 }
					 else
					 {
						 System.out.println("inside waitlist");
						 String waitlist = "select min(Ddate) from report where BISBN = ? and IsReturned = 'N' and waitlistFlag = 'N'";
						 String waitDate = null;
						 PreparedStatement ps4 = conn.prepareStatement(waitlist);
						 ps4.setInt(1, isbn);
						 ResultSet waitres = ps4.executeQuery();
						 if(waitres.next())
						 {
							waitDate = waitres.getDate(1).toString(); 
						 }
						 out.println("Sorry,we are out of stock for this book .You might get it on " +waitDate+" .You may borrow the book on this date.CLick yes to borrow");
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
			if(ano==9) // To waitlist the book
			{
				System.out.println("inside servlet waitlist");
				
				String bname1 = request.getParameter("bname");
				String mid1 = request.getParameter("mid");
				int isbn1 = Integer.parseInt(request.getParameter("isbn"));
				
				String dateQuery =  "select min(Ddate) from report where BISBN = ? and IsReturned = 'N' and waitlistFlag = 'N'";
				String borrowQuery3 = "INSERT INTO report(MID,Bisbn,Bdate,Ddate,IsReturned,fine,Bname,waitlistFlag) values ((select MID from user where username =?),?,?,(DATE_ADD(?, INTERVAL 10 DAY)),'N',0,?,'N')";
				String updateQuery = "update report set waitlistFlag = 'Y' where Ddate = (select * from (select min(Ddate) from report where BISBN = ? and waitlistFlag = 'N') as ddate) and BISBN = ?";
				String selectQuery = "select Bdate from report where BISBN = ? and MID = (select MID from user where username = ?)";
				Connection conn = new DBConnection().getDbconnection();
				try {
					
					PreparedStatement waitPs0 = conn.prepareStatement(dateQuery);
					PreparedStatement waitPs1 = conn.prepareStatement(borrowQuery3);
					PreparedStatement waitPs2 = conn.prepareStatement(updateQuery);
					
					waitPs0.setInt(1,isbn1);
					waitPs1.setString(1,mid1);
					waitPs1.setInt(2, isbn1);					
					waitPs1.setString(5, bname1);
					
					ResultSet dateResult = waitPs0.executeQuery();
					if(dateResult.next())
					{
						Date minDate = dateResult.getDate(1);
					
					waitPs1.setDate(3, minDate);
					waitPs1.setDate(4, minDate);
					waitPs2.setInt(1, isbn1);
					waitPs2.setInt(2, isbn1);
					int noOfRows1 = waitPs1.executeUpdate();
					int noOfRows2 = waitPs2.executeUpdate();
					if(noOfRows1 > 0 && noOfRows2 > 0)
					{
						System.out.println("inside servlet waitlist branch");
						PreparedStatement waitPs3 = conn.prepareStatement(selectQuery);
						waitPs3.setInt(1, isbn1);
						waitPs3.setString(2, mid1);
						ResultSet waitRes = waitPs3.executeQuery();
						if(waitRes.next())
						{
							System.out.println("inside servlet waitlist if");
							String waitDdate = waitRes.getDate(1).toString();
							out.println("Thanks for borrowing .You may collect it on "+ minDate.toString()+" .");
						}
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
			if(ano == 10) 
			{
				int bIsbn  = Integer.parseInt(request.getParameter("bookISBN"));
				String bAuthor = request.getParameter("bookAuthor");
				int bCopies = Integer.parseInt(request.getParameter("bookCopies"));
				String bName = request.getParameter("bookName");
				
				ResultSet rs2 = null;
				
				System.out.println("inside abhi jaffa");
				
				String query = "insert into book(BISBN,Bauthor,Bnoofcopies,Bname) values (?,?,?,?) ";
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1,bIsbn);
					
					ps.setString(2,bAuthor);
					ps.setInt(3,bCopies);
					ps.setString(4,bName);
					
					int no_of_rows = ps.executeUpdate();

					if(no_of_rows == 1) // if the insert is successfull generate an unique id to the user
					{
						
						out.println("Book is added successfully ");
					}
					}		
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						if(rs2 != null)
						{
						rs2.close();
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
			

			
			if(ano == 11) 
			{
				int bISbn  = Integer.parseInt(request.getParameter("bookISBN"));
				
				
				
				ResultSet rs3 = null;
				
				System.out.println("inside abhi jaffa");
				
				String query = "delete from book where BISBN = ? ";
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1,bISbn);
					
					
					
					int no_of_rows = ps.executeUpdate();

					if(no_of_rows == 1) // if the insert is successfull generate an unique id to the user
					{
						
						out.println("Book record is deleted successfully ");
					}
					}		
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						if(rs3 != null)
						{
						rs3.close();
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
			

			
			
			if(ano == 12) 
			{
				int bISbn  = Integer.parseInt(request.getParameter("bookISBN"));
				int bCopies = Integer.parseInt(request.getParameter("bookCopies"));

				System.out.println("hiiiiiiiiii"+bISbn);
				System.out.println("hiiiiiiiiii"+bCopies);
				
				
				
				
				System.out.println("inside abhi jaffa");
				
				String query = "update book set Bnoofcopies = ? where BISBN = ? ";
				Connection conn = new DBConnection().getDbconnection();
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1,bCopies);
					ps.setInt(2,bISbn);
					System.out.println("u are a jaffa");
					
					
					
					int no_of_rows = ps.executeUpdate();
					System.out.println("no_of_rows" + no_of_rows);

					if(no_of_rows == 1) // if the insert is successfull generate an unique id to the user
					{
						System.out.println("u are a duffer");
						
						out.println("Book record is updated successfully ");
					}
					}		
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					try {
						
					
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
			if (ano == 13)
			{
				String sessionid = request.getParameter("sessionid");
				int isbn = Integer.parseInt(request.getParameter("isbn"));
				String cartQuery = "insert into cart(MID,Bname) values ((select MID from user where username = ?),(select Bname from book where BISBN = ?))";
				Connection conn = new DBConnection().getDbconnection();
				try {
					
					PreparedStatement cartStatement = conn.prepareStatement(cartQuery);
					cartStatement.setString(1, sessionid);
					cartStatement.setInt(2, isbn);
					int cartEntries = cartStatement.executeUpdate();
					if(cartEntries > 0)
					{
						System.out.println("inside servlet cart");
						out.println("Y");
					}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
				finally
				{
					try{
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
			if(ano == 14)
			{
				String cartbooks = "";
                ResultSet cartset = null;
				String sessionid = request.getParameter("sessionid");				
				String cartsets = "select Bname from cart where MID = (select MID from user where username = ?)";
				Connection conn = new DBConnection().getDbconnection();
				try {
					
					PreparedStatement cartStatement = conn.prepareStatement(cartsets);
					cartStatement.setString(1, sessionid);					
					cartset = cartStatement.executeQuery();
					while(cartset.next())
					{
						cartbooks = cartbooks + cartset.getString(1)+":";
						System.out.println(cartbooks);
						System.out.println("inside cart not working");
					}
					String finalcart = cartbooks.substring(0,(cartbooks.length()-1) );
					System.out.println(finalcart);
					out.println(finalcart);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
				finally
				{
					try{
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
			if(ano == 15)
			{
				String cartbooks = "";
                ResultSet studyRooms = null;
                String endTime = null;
                //DateFormat initstudyTime = new SimpleDateFormat("hh:mm:ss");
				String sessionid = request.getParameter("sessionid");
				System.out.println("15"+sessionid);				
				String sdate = request.getParameter("myDate");
				System.out.println("15"+sdate);
				String stime = request.getParameter("myTime");
				System.out.println("15"+stime);
				DateFormat studyTime = new SimpleDateFormat("hh:mm:ss");
				try {
					java.util.Date studyDate =  studyTime.parse(stime);
					Calendar studyCal = new GregorianCalendar();
					studyCal.setTime(studyDate);
					studyCal.add(Calendar.HOUR, 2);
					java.util.Date addDate =  studyCal.getTime();
					Calendar studyCal1 = Calendar.getInstance();
					studyCal1.setTime(addDate);
					Integer hour = studyCal1.get(Calendar.HOUR);
					Integer minute = studyCal1.get(Calendar.MINUTE);
					Integer second = studyCal1.get(Calendar.SECOND);
				    endTime = hour.toString()+":"+minute.toString()+":"+second.toString();
					 //endTime = addDate.toString();
					 System.out.println("15"+endTime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String studyRow = "select 1 from studyroom where Sdate = ? and Stime <= time_format(?,'%H:%i:%s') and Etime > time_format(?,'%H:%i:%s')";
				String newStudyRow = "insert into studyroom(Rno,assignTo,Sdate,Stime,Etime) values(1,?,?,?,?)";
				Connection conn = new DBConnection().getDbconnection();
				try {
					
					PreparedStatement studyStatement = conn.prepareStatement(studyRow);
					studyStatement.setString(1, sdate);
					studyStatement.setString(2, stime);
					studyStatement.setString(3, stime);
					studyRooms = studyStatement.executeQuery();
					if(studyRooms.next())
					{
						out.println("N");
						System.out.println("inside study not working");
					}
					else
					{
						PreparedStatement studynewStatement = conn.prepareStatement(newStudyRow);
						studynewStatement.setString(1, sessionid);
						studynewStatement.setString(2, sdate);
						studynewStatement.setString(3, stime);
						studynewStatement.setString(4, endTime);
						
						
						int no_of_rows = studynewStatement.executeUpdate();
						if(no_of_rows > 0)
						{
							out.println("Slot available. Study room booked. Thankk you!");
						}
					}
					
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
				finally
				{
					try{
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
			if(ano == 16) 
			{
				String query0 = "select Ddate from report where curdate()>Ddate";
				
				String query1 = "update report set fine = ? where Ddate = ? and curdate() > Ddate";
				String query2 = "select MID,BISBN,Ddate,fine from report";
				Connection conn = new DBConnection().getDbconnection();
				Statement st = null;
				Statement st1 = null;
				ResultSet rs4 = null;
				ResultSet rs5 = null;
				String data = "";
				try {
					PreparedStatement reportStat = conn.prepareStatement(query1);
					st = conn.createStatement();
					st1 = conn.createStatement();
					rs4 = st1.executeQuery(query0);
					while(rs4.next())
					{
						
						long fine = 0;
						Date dddate = rs4.getDate(1);
						String duedate = dddate.toString();
						//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date curdate = new java.util.Date();
						String todaysDate = curdate.toString();
						//String d = dateFormat.format(curdate);
						SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
						//Calendar reportCal = new GregorianCalendar();
						//studyCal.setTime(studyDate);
						//studyCal.add(Calendar.HOUR, 2);
						
						Calendar reportCal = Calendar.getInstance();
						//java.util.Date reportDate =  reportCal.getTime();
						//studyCal1.setTime(addDate);
						Integer day = reportCal.get(Calendar.DAY_OF_MONTH);
						Integer month = reportCal.get(Calendar.MONTH);
						Integer year = reportCal.get(Calendar.YEAR);

						long d1=formater.parse(year.toString()+"-"+month.toString()+"-"+day.toString()).getTime();
						long d2=formater.parse(duedate).getTime();
						long noofdays = Math.abs((d1-d2)/(1000*60*60*24));
						fine = 2*noofdays;
						reportStat.setLong(1, fine);
						reportStat.setDate(2, dddate);
						int noofrows = reportStat.executeUpdate();
						
					}
					
					
					//st = null;
					rs5 = st.executeQuery(query2);
					   
					if(rs5.next())
					   {
						   System.out.println("success");
					
				}else
				   {
					   out.println("");
				   }
					
					while(rs5.next())
					{
						Integer Mid = rs5.getInt(1);
						Integer BISBN = rs5.getInt(2);
						Integer fine = rs5.getInt(4);
						Date Ddate = rs5.getDate(3);
						String date_1 = Ddate.toString();
						data = data+Mid.toString()+ ":" + BISBN.toString()+ ":" + date_1+":"+fine.toString()+ ";" ;
						
						
						
					}
					
					finalstr = data.substring(0,(data.length()-1) );
					System.out.println(finalstr);
					out.println(finalstr);
				   
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					
				
				
				finally
				{
					try {
						rs4.close();					
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

