<%-- 
    Document   : errorpage
    Created on : May 30, 2022, 4:43:53 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>hieu chua</h1>
        <h2><%= exception.getMessage() %></h2>
    </body>
</html>
