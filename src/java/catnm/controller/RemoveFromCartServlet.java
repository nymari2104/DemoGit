/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.controller;

import catnm.cart.CartBean;
import catnm.tblProduct.TblProductDAO;
import catnm.tblProduct.TblProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
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
@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/RemoveFromCartServlet"})
public class RemoveFromCartServlet extends HttpServlet {

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
        try {
            //1. Cust goes to his/her carts place
            HttpSession session = request.getSession(false);//must false to check it available
            if (session != null) {
                //2. Cust goes to his/her cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust get items
                    Map<TblProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Cust check each item to remove
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            for (String item : selectedItems) {
                                //get dto of product
                                TblProductDAO dao = new TblProductDAO();
                                TblProductDTO dto = dao.searchById(item);
//                                //recover quantity of product
//                                dao.updateQuantity(item, dto.getQuantity() + cart.getQuantityOfItem(dto));
                                cart.removeItemFromCart(dto);
                            }//each item is checked
                            session.setAttribute("CART", cart);
                        }//user did NOT choose checkbox
                    }//items have existed
                }//cart has existed
            }//carts place has existed
        } catch (SQLException ex) {
            log("RemoveFromCartServlet_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("RemoveFromCartServlet_Naming" + ex.getMessage());
        } finally {
            //5. refresh call previous function using URL Rewriting
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View cart";
            response.sendRedirect(urlRewriting);//btAction accur 2 times
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
