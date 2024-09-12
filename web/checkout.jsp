<%-- 
    Document   : checkout
    Created on : Jun 23, 2024, 10:16:28 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="catnm.tblProduct.TblProductDAO"%>
<%@page import="catnm.orderDetail.OrderDetailDTO"%>
<%@page import="java.util.LinkedList"%>
<%@page import="catnm.tblOrder.TblOrderDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
    </head>
    <body>
        <c:set var="order" value="${requestScope.CUSTOMER_INFO}"/>
        <c:set var="orderDetail" value="${requestScope.CUSTOMER_DETAIL_INFO}"/>
        <c:set var="productList" value="${sessionScope.LIST}"/>
        <c:set var="total"/>
        <c:if test="${order ne null and orderDetail ne null}">
            <h3>Id: ${order.id}</h3>
            <h3>Name: ${order.customer}</h3>
            <h3>Address: ${order.address}</h3>
            <h3>Email: ${order.email}</h3>
            <h3>Date: ${order.date}</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Book</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${orderDetail}">
                        <c:forEach var="productName" items="${productList}">
                            <c:if test="${product.productId eq productName.sku}">
                                <tr>
                                    <td>${productName.name}</td>
                                    <td>${product.quantity}</td>
                                    <td>${product.unitPrice}</td>   
                                    <td>${product.total}</td>
                                </tr>
                                <c:set var="total" value="${total + product.total}"/>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                <td colspan="3">
                    Total
                </td>
                <td>
                    ${total}
                </td>
            </tbody>
        </table>

        <a href="DispatchServlet?btAction=Show Product List">Buy book again!!!</a>
    </c:if>
    <c:if test="${order eq null or orderDetail eq null}">
        <font color="red">
        you don't have any order!!!!
        </font><br/>
        <a href="DispatchServlet?btAction=Show Product List">Buy book!</a>
    </c:if>
    <%-- <%
         List<TblOrderDTO> orderResult = (List<TblOrderDTO>) request.getAttribute("CUSTOMER INFO");
         List<OrderDetailDTO> orderDetailResult = (List<OrderDetailDTO>) request.getAttribute("CUSTOMER DETAIL INFO");
         if (orderResult != null && orderDetailResult != null) {
     %>
     <h2>Name: <%= orderResult.get(0).getCustomer()%></h2>
     <h2>Address: <%= orderResult.get(0).getAddress()%></h2>
     <h2>Email: <%= orderResult.get(0).getEmail()%></h2>
     <h2>size of orderDetail: <%= orderDetailResult.size() %></h2>
     <%
         for (TblOrderDTO order : orderResult) {
     %>
     <h2><%= order.getDate()%></h2>
     <%
         for (OrderDetailDTO orderDetail : orderDetailResult) {
                 %>
                 <h2>book: <%= orderDetail.getProductId() %></h2>
                 <h2>quantity: <%= orderDetail.getQuantity() %></h2>
                 <h2>price: <%= orderDetail.getUnitPrice() %></h2>
                 <h2>total: <%= orderDetail.getTotal() %></h2>
     <%
             }
         }
     %>
     <a href="DispatchServlet?btAction=Show Product List">Buy book again!!!</a>
     <%
         }
     %> --%>
</body>
</html>
