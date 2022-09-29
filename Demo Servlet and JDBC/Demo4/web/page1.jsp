<%-- 
    Document   : page1
    Created on : May 27, 2022, 4:16:29 PM
    Author     : Nghia
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            //khai bao declaration
            //khai bao fiels/methods
            int count=1;
            public void incre() {
                count++;
            }
        %>
        <!--code html -->
        <h1>Ohayo gozaimasu</h1>
        <h2>pass mon eiiii</h2>
        <%//code java
            //scriptlet
            String s = "chau len 3";
            Date d = new Date();
            request.setAttribute("test1", 1);
            response.sendRedirect("index.html");
            application.setAttribute("test2", 2);
            out.println("<p>hong vui huhu</p>");
        %>
        <p>hong vui huhu</p>
    </body>
</html>
