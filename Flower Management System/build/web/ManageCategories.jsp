<%-- 
    Document   : ManageCategories
    Created on : Jun 25, 2022, 11:21:40 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>

        <h1></h1>
        <table class="order">
            <tr><th> Cate ID</th>
                <td> Cate Name</td>
            </tr>
            
            <c:forEach var="cate" items="${requestScope.categoriesList}">
                <tr><td><c:out value="${cate.getCateID()}"></c:out></td>
                    <td><c:out value="${cate.getCateName()}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
