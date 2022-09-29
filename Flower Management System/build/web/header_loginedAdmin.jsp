<%-- 
    Document   : header_loginedAdmin
    Created on : Jun 23, 2022, 10:44:27 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <li><a href="mainController?action=manageAccounts">Manage Accounts</a></li>
            <li><a href="mainController?action=manageOrders">Manage Orders</a></li>
            <li><a href="mainController?action=managePlants">Manage Plants</a></li>
            <li><a href="mainController?action=manageCategories">Manage Categories</a></li>
            <li>Welcome ${sessionScope.name} | <a href="mainController?action=logout">Logout</a></li>
        </header>
    </body>
</html>
