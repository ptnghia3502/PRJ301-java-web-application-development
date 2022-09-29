<%-- 
    Document   : ManageOrders
    Created on : Jun 24, 2022, 11:34:27 PM
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
        <form action="mainController" method="post">
            <input type="text" name="txtSearch">
            <input type="submit" value="searchOrderByEmail" name="action">
        </form>
        <h1></h1>
        <table class="order">
            <tr><th> Order ID</th>
                <td> Order Date</td>
                <td> Ship date</td>
                <td> Status</td>
                <td> Acc ID</td>
                <td> Action</td>
            </tr>
            
            <c:forEach var="acc" items="${requestScope.ordersList}">
                <tr><td><c:out value="${acc.getOrderID()}"></c:out></td>
                    <td><c:out value="${acc.getOrderDate()}"></c:out></td>
                    <td><c:out value="${acc.getShipDate()}"></c:out></td>
                    <td><c:choose>
                            <c:when test="${acc.getStatus() eq 1}">Processing</c:when>
                            <c:when test="${acc.getStatus() eq 2}">Completed</c:when>
                            <c:when test="${acc.getStatus() eq 3}">Canceled</c:when>
                        </c:choose>
                    </td>
                    <td><c:out value="${acc.getAccID()}"></c:out></td>
                    <td><a href="orderDetail.jsp?orderid=${acc.getOrderID()}">detail</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
