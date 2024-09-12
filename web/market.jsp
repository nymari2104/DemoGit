<%-- 
    Document   : market
    Created on : Jun 18, 2024, 10:37:02 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="catnm.tblProduct.TblProductDTO"%>
<%@page import="catnm.tblProduct.TblProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Market</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Book Market</h1>
        <form action="DispatchServlet">
            <c:set var="listProduct" value="${sessionScope.LIST}"/>
            <c:set var="lastSelected" value="${sessionScope.CURRENT}"/>
            <c:set var="errors" value="${sessionScope.ADD_TO_CART_ERROR}"/>
            Choose book <select name="ddlBook">
                <c:forEach var="dto" items="${listProduct}">
                    <c:if test="${dto.quantity gt 0 and dto.status eq true}">
                        <option value="${dto.sku}" 
                                <c:if test="${lastSelected eq dto.sku}">
                                    selected
                                </c:if>
                                >
                            ${dto.name} 
                            price: ${dto.price}$
                            available: ${dto.quantity}
                        </option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="number" name="quantity" value="1" /><br/>
            <c:if test="${errors ne null}">
                <font color="red">${errors.invalidQuantityErr}
                                  ${errors.quantityAndStatusErr}</font>
            </c:if><br/>
            <input type="submit" value="Add Book to Your Cart" name="btAction" />
            <input type="submit" value="View cart" name="btAction" /><br/>
            <a href="login.html">Back to login</a>
        </form>
        <%--        <form action="DispatchServlet">
                    Choose book <select name="ddlBook">
                        <%
                            TblProductDAO dao = new TblProductDAO();
                            List<TblProductDTO> product = dao.getProduct();
                            for (TblProductDTO dto : product) {
                                if (dto.isStatus()) {
                        %>
                        <option><%= dto.getName()%></option>
                        <%
                                }
                            }
                        %>
                    </select><br/>
                    <input type="submit" value="Add Book to Your Cart" name="btAction" />
                    <input type="submit" value="View cart" name="btAction" />
                </form> --%>
    </body>
</html>
