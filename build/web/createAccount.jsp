<%-- 
    Document   : createAccount
    Created on : Jun 27, 2024, 8:02:59 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />
        <form action="DispatchServlet" method="POST">
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.userNameLengthErr}">
                <font color="red">
                    ${errors.userNameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password*<input type="password" name="txtPassword" value="" />(6 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                    ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm*<input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full name*<input type="text" name="txtFullname" 
                             value="${param.txtFullname}" />(2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                    ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create Account" name="btAction" />
            <a href="login.html">Login</a><br/>
            <input type="reset" value="Reset" />
            
        </form>
    </body>
</html>
