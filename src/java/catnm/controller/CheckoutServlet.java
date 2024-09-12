/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catnm.controller;

import catnm.cart.CartBean;
import catnm.orderDetail.OrderDetailDAO;
import catnm.orderDetail.OrderDetailDTO;
import catnm.tblOrder.TblOrderDAO;
import catnm.tblOrder.TblOrderDTO;
import catnm.tblProduct.TblProductDAO;
import catnm.tblProduct.TblProductDTO;
import catnm.tblProduct.TblProductError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

//    private final String ERROR_PAGE = "error.html";
    private final String CHECKOUT_PAGE = "checkout.jsp";
    private final String SHOW_ERROR_PAGE = "viewCart.jsp";

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
        String url = CHECKOUT_PAGE;
        boolean foundErr = false;
        TblProductError errors = new TblProductError();
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(false);// false to check it available or null
            if (session != null) {
                //2. Cust takes his/her cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust take items
                    Map<TblProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Each item put into DB
                        //4.1 Create tblOrder object
                        //4.1.1 get all user's information
                        String customer = request.getParameter("txtCustomer");
                        String address = request.getParameter("txtAddress");
                        String email = request.getParameter("txtEmail");
                        //get time when user checkout
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        //4.1.2 call method of Model
                        TblOrderDAO orderDao = new TblOrderDAO();
                        boolean orderResult = orderDao.createOrder(customer, address, email, sqlDate);
//                        if (orderResult) {
//                            url = 
//                        }
                        orderDao.checkout(customer, address, email, sqlDate);
                        List<TblOrderDTO> orderDto = orderDao.getOrder();
                        //take the last order of cust
                        request.setAttribute("CUSTOMER_INFO", orderDto.get(orderDto.size() - 1));

                        //4.2 Create OrderDetail object
                        //4.2.1 get details order of cust
                        String orderId = orderDto.get(orderDto.size() - 1).getId();
                        //4.2.2 call method of Model
                        OrderDetailDAO orderDetailDao = new OrderDetailDAO();
                        TblProductDAO productDao = new TblProductDAO();
                        List<String> productErr = new ArrayList<>();//which product has error
                        for (TblProductDTO key : items.keySet()) {
                            int quantity = items.get(key);
                            //4.2.3 process result
                            orderDetailDao.createOrderDetail(key.getSku(), key.getPrice(), quantity, orderId);
                            //check quantity and status of product
                            List<TblProductDTO> productList = productDao.getProduct();

                            for (TblProductDTO list : productList) {
                                if (key.getSku().equals(list.getSku())) {
                                    if (items.get(key) > list.getQuantity() || list.isStatus() != true) {
                                        foundErr = true;
                                        errors.setQuantityAndStatusErr("Quantities of this item is not enough or not in stock now");
                                        productErr.add(list.getSku());
                                    }//check quantity and status of product in stock
                                    if (!foundErr) {
                                        productDao.updateQuantity(list.getSku(), list.getQuantity() - items.get(key));//update quantities of product when user checkout
                                    }
                                }
                            }

                        }
                        if (foundErr) {//errors
                            url = SHOW_ERROR_PAGE;
                            request.setAttribute("CHECKOUT_ERROR", errors);
                            request.setAttribute("PRODUCT_ERROR", productErr);
                        } else {//no errors

                            List<OrderDetailDTO> orderDetailDto = orderDetailDao.getOrderDetail(orderId);
                            request.setAttribute("CUSTOMER_DETAIL_INFO", orderDetailDto);
                            //5. remove attribute
                            session.removeAttribute("CART");
                        }
                    }//items has existed
                }//cart has existed
            }//cart place has existed
        } catch (SQLException ex) {
            log("CheckoutServlet_SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutServlet_Naming" + ex.getMessage());
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
