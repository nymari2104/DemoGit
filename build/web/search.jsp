<%-- 
Document   : search
Created on : Jun 6, 2024, 7:42:07 AM
Author     : DELL
--%>

<%-- <%@page import="catnm.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@ page session="false" %> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="btAction" id="logout"/>
        </form>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.name}
                                    <input type="hidden" name="txtUsername" value="${dto.name}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}"/>
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="Role" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.name}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Update"/>
                                    <input type="hidden" name="searchLastname" 
                                           value="${searchValue}"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color="red">
                No record is matched!!!
                </font>
            </h2>
        </c:if>
    </c:if>
    <%-- <%
         //1. get cookies
         Cookie[] cookies = request.getCookies();
         //2. check existed cookies
         if (cookies != null) {
             Cookie recentCookie = cookies[cookies.length - 1];
             String username = recentCookie.getName();
     %>
     <h2 color="red">
         Welcome, <%= username %>
     </h2>
     <%
         }
     %>
     <h1>Search Page</h1>
     <form action="DispatchServlet">
         Search value <input type="text" name="txtSearchValue" 
                             value="<%= request.getParameter("txtSearchValue")%>" /> <br/>
         <input type="submit" value="Search" name="btAction" />
     </form>
     <br/>
     <%
         String searchValue = request.getParameter("txtSearchValue");
         if (searchValue != null) {
             List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
             if (result != null) {//searh has result
     %>
     <table border="1">
         <thead>
             <tr>
                 <th>No.</th>
                 <th>Username</th>
                 <th>Password</th>
                 <th>Full name</th>
                 <th>Role</th>
                 <th>Delete</th>
                 <th>Update</th>
             </tr>
         </thead>
         <tbody>
             <%
                 int count = 0;
                 for (RegistrationDTO dto : result) {
                     String urlRewriting = "DispatchServlet"
                             + "?btAction=delete"
                             + "&pk=" + dto.getName()
                             + "&lastSearchValue=" + searchValue;
             %>
         <form action="DispatchServlet" method="POST">
             <tr>
                 <td>
                     <%= ++count%>
                     .</td>
                 <td>
                     <%= dto.getName()%>
                     <input type="hidden" name="txtUsername" 
                            value="<%= dto.getName()%>" />
                 </td>
                 <td>
                     <input type="password" name="txtPassword" 
                            value="<%= dto.getPassword()%>" />
                 </td>
                 <td>
                     <input type="checkbox" name="Role" value="True"
                            <%
                                if (dto.isRole()) {
                            %>
                            checked="checked"
                            <%
                                }//admin
%>
                            />
                 </td>
                 <td>
                     <%= dto.isRole()%>
                 </td>
                 <td>
                     <a href="<%= urlRewriting%>">Delete</a>
                 </td>
                 <td>
                     <input type="submit" value="Update" name="btAction" />
                     <input type="hidden" name="searchLastname" 
                            value="<%= searchValue%>" />
                 </td>
             </tr>
         </form>
         <%
             }//travrese Result
         %>
     </tbody>
 </table>

    <%
    }    else {
    %>
    <h2>
        No record is matched!!!!
    </h2>
    <%
            }
    %> --%>
    
</body>
</html>
