<%-- 
    Document   : searchpage
    Created on : May 27, 2022, 5:14:19 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SearchServlet" method="post">
            <input type="text" name="txtsearch"/>
            <input type="submit" value="search"/>
        </form>
        <%
            String s = (String) request.getAttribute("warning");
        %>
        <h2><%= (s != null)? s:"" %></h2>
    </body>
</html>
