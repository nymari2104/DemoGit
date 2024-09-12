<%-- 
    Document   : viewCart
    Created on : Jun 17, 2024, 8:28:03 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="catnm.cart.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your cart includes</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:set var="items" value="${cart.items}"/>
        <c:set var="errors" value="${requestScope.CHECKOUT_ERROR}"/>
        <c:set var="itemsErr" value="${requestScope.PRODUCT_ERROR}"/>
        <c:if test="${items eq null}">
            <font color="red">
            No cart is existed!!!!
            <font/><br/>
            <a href="DispatchServlet?btAction=Show Product List">Back to the shop</a>
        </c:if>
        <c:if test="${items ne null}">
            <form action="DispatchServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${items}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${product.key.name}<br/>
                                    <c:forEach var="error" items="${itemsErr}">
                                        <c:if test="${error eq product.key.sku}">
                                            <font color="red">${errors.quantityAndStatusErr}</font>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    ${product.value}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${product.key.sku}" />
                                </td>
                            </tr>
                        </c:forEach>
                    <td colspan="3">
                        <a href="DispatchServlet?btAction=Show Product List">Add more Books to your Cart</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove Selected Items" name="btAction" />
                    </td>
                    </tbody>
                </table>
            </form>
            <form action="DispatchServlet">
                Name*<input type="text" name="txtCustomer" value="" required/><br/>
                Address*<input type="text" name="txtAddress" value="" required/><br/>
                Email*<input type="email" name="txtEmail" value="" required/><br/>
                <input type="submit" value="Check Out" name="btAction" />
            </form>
        </c:if>
        <%--        <%
                    //1. Cust goes to his/her cart
                    if (session != null) {
                        //2. Cust takes his/her cart
                        CartBean cart = (CartBean) session.getAttribute("CART");
                        if (cart != null) {
                            //3. Cust checks existed items
                            Map<String, Integer> items = cart.getItems();
                            if (items != null) {
                                //4. Cust gets all item
                %>
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (String key : items.keySet()) {
                            %>
                            <tr>
                                <td>
                                    <%= ++count%>
                                    .</td>
                                <td>
                                    <%= key%>
                                </td>
                                <td>
                                    <%= items.get(key)%>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="<%= key%>" />
                                </td>
                            </tr>
                            <%
                                }//each entry is processed
                            %>
                        <td colspan="3">
                            <a href="market.html">Add more Books to your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btAction" />
                        </td>
                        </tbody>
                    </table>
                </form>
                <form action="DispatchServlet">
                    Name*<input type="text" name="txtCustomer" value="" required/><br/>
                    Address*<input type="text" name="txtAddress" value="" required/><br/>
                    Email*<input type="email" name="txtEmail" value="" required/><br/>
                    <input type="submit" value="Check Out" name="btAction" />
                </form>
                <%
                                return;
                            }//item must be existed
                        }//cart must be existed
                    }//cart place must be existed
        %>
                <h2>
                    <font color="red">
                    No cart is existed!!!!
                    <font/>
                </h2> --%>
    </body>
</html>
