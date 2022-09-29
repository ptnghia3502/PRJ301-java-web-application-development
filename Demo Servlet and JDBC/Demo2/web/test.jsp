<%-- 
    Document   : test
    Created on : Jun 20, 2022, 5:12:24 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty request.Scope.listPlants}">
        <c:forEach items="${ requestScope.listPlants}" var="p">
            <table>
                <tr>
                    <td>${ p.getId() }</td>
                    <td>${ p.name }</td>
                </tr>
            </table>
        </c:forEach>
        </c:if>
    </body>
</html>
