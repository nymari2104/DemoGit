/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.controller;

import catnm.cart.CartBean;
import catnm.tblProduct.TblProductDAO;
import catnm.tblProduct.TblProductDTO;
import catnm.tblProduct.TblProductError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddtoCartServlet", urlPatterns = {"/AddtoCartServlet"})
public class AddtoCartServlet extends HttpServlet {

//    private final String MARKET_PAGE = "market.jsp";
    private final String MARKET_PAGE = "DispatchServlet?btAction=Show Product List";

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
        String url = MARKET_PAGE;
        boolean foundErr = false;
        TblProductError errors = new TblProductError();
        try {
            //1. Cust goes to the cart place
            HttpSession session = request.getSession(); //cart place must be available
            //2. Cust takes his/her cart
            CartBean cart = (CartBean) session.getAttribute("CART");//named by your self
            if (cart == null) {
                cart = new CartBean();
            }//end cart has not existed
            //3. Cust drops a item to his/her cart
            String item = request.getParameter("ddlBook");
            
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            //get product
            TblProductDAO dao = new TblProductDAO();
            TblProductDTO dto = dao.searchById(item);
            //check the valid quantities 
            if (quantity < 1) {// Cust order zero or negative quantities
                    foundErr = true;
                    errors.setInvalidQuantityErr("You cannot order less than 1");
                }
                if (foundErr) {//errors
                    session.setAttribute("ADD_TO_CART_ERROR", errors);
                } else {//no errors
                    cart.addItemToCart(dto, quantity);
                    session.setAttribute("CART", cart);
                }
            session.setAttribute("CURRENT", item);
        } catch (SQLException ex) {
            log("AddtoCartServlet_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("AddtoCartServlet_Naming" + ex.getMessage());
        } finally {
            //4. Cust goes to shopping
            response.sendRedirect(url);
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
