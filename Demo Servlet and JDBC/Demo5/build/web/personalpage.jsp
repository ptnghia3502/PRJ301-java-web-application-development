<%-- 
    Document   : personalpage
    Created on : Jun 6, 2022, 5:28:01 PM
    Author     : Nghia
--%>

<%@page import="basicobject.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Welcome, <%= ((Account)session.getAttribute("loginedUser")).getFullname() %></h3>
        <h4><a href="mainController?action=logout">log out</a></h4>
        <h4><a href="mainController?action=viewcart">View cart</a></h4>
    </body>
</html>
