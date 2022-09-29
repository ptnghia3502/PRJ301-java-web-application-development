<%-- 
    Document   : page3
    Created on : May 27, 2022, 5:04:33 PM
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
        <%
            //lay count=1 do page 2 truyen qua
            int value = (int) request.getAttribute("count");
            out.println("<h1>value nhan duoc tu page:" + value + "</h1>");
        %>
        <h1>value nhan duoc tu page 2: <%= value %></h1> <!--expression-->
    </body>
</html>
