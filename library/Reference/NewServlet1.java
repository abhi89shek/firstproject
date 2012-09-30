/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Enumeration;

/**
 *
 * @author Shrikant
 */
public class NewServlet1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name;
        PrintWriter out = response.getWriter();
        String inpDate = null;
        int shiftid = 0;
        int mealBreak = 0;
        int shortBreak = 0;
        int totalPieces = 0;
        int rejectedPieces = 0;
        int downTime = 0;

        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            name = (String) paramNames.nextElement();
            String[] values = request.getParameterValues(name);

            int i = 0;
            if (name.equals("date")) {
                inpDate = values[i].toString();
            }
            if (name.equals("shiftid")) {
                shiftid = Integer.parseInt(values[i].toString());

            }
            if (name.equals("mealbreak")) {
                mealBreak = Integer.parseInt(values[i].toString());

            }
            if (name.equals("shorBreak")) {
                shortBreak = Integer.parseInt(values[i].toString());

            }
            if (name.equals("totalPieces")) {
                totalPieces = Integer.parseInt(values[i].toString());
            }
            if (name.equals("rejectedPieces")) {
                rejectedPieces = Integer.parseInt(values[i].toString());

            }
            if (name.equals("downTime")) {
                downTime = Integer.parseInt(values[i].toString());

            }
            i++;
        }

        double availability = (double) (480 - mealBreak - shortBreak - downTime) / 480;
        double quality = (double) (totalPieces - rejectedPieces) / totalPieces;
        double performance = (double) (7999 / (double) 8000);
        double oee = (double) availability * quality * performance;


        Connection con = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1,:3306/sgd8088?user=root");
            Statement st = con.createStatement();
            String query2 = "call Insertshiftdetails(" + shiftid + "," + "'" + inpDate + "'" + "," + downTime + "," + shortBreak + "," + mealBreak + "," + totalPieces + "," + rejectedPieces + ",8000)";
            rs = st.executeQuery(query2);
            String query1 = "call InsertIntoOee(" + shiftid + "," + "'" + inpDate + "'" + "," + availability + "," + quality + "," + performance + "," + oee + ")";
            rs = st.executeQuery(query1);
            out.println("Successfully entered");

        } catch (Exception e) {
            out.println("Check input values");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                out.println("Connection problem");
            }


        }


    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    //@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
