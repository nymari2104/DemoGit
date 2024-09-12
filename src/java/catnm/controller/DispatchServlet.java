/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    
            
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String STARTUP_COTROLLER = "StartUpServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddtoCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER ="RemoveFromCartServlet";
    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
    private final String ADD_ACCOUNT_CONTROLLER = "CreateAccountServlet";
    private final String SHOW_PRODUCT_CONTROLLER = "ShowProductListServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
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
        //Which button did user click?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        
        try {
            if (button == null) {//first time
                //check all request
                url = STARTUP_COTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            }else if (button.equals("delete")) {//user clicked delete link
                url = DELETE_ACCOUNT_CONTROLLER;
            }else if (button.equals("Update")){
                url = UPDATE_ACCOUNT_CONTROLLER;
            }else if (button.equals("Add Book to Your Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            }else if (button.equals("View cart")) {
                url = VIEW_CART_PAGE;
            }else if (button.equals("Remove Selected Items")) {
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            }else if (button.equals("Check Out")) {
                url = CHECKOUT_CONTROLLER;
            }else if (button.equals("Create Account")) {
                url = ADD_ACCOUNT_CONTROLLER;
            }else if (button.equals("Show Product List")) {
                url = SHOW_PRODUCT_CONTROLLER;
            }else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
