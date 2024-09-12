/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.controller;

import catnm.registration.RegistrationDAO;
import catnm.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class LoginServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalid.html";
//    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String SEARCH_PAGE = "search.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

//        String button = request.getParameter("btAction");
        String url = INVALID_PAGE;
        //1. get all user's information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {
//            if (button.equals("Login")) {
                //2.Call method of Model
                //2.1 New DAO Object
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 Call method of DAO
                RegistrationDTO result = dao.checkLogin(username, password);
                //3. Process result
                if (result != null) {
                    url = SEARCH_PAGE;
                    //open session
                    HttpSession session = request.getSession();//create new session when user login success
                    session.setAttribute("USER", result);
                    session.setAttribute("USERNAME", username);
                    //sending cookies
//                    Cookie cookie = new Cookie(username, password);
//                    cookie.setMaxAge(60 * 30);
//                    response.addCookie(cookie);
                }//user is authenticated
//            }//user clicked Login button
        } catch (NamingException ex) {
            log("LoginServlet_SQL" + ex.getMessage());  
        } catch (SQLException ex) {
            log("LoginServlet_Naming" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
