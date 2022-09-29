<%-- 
    Document   : page5
    Created on : Jun 15, 2022, 5:03:21 PM
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
        <jsp:useBean id="obj" class="basic.Book" scope="session">
            <jsp:setProperty property="id" name="obj" value="1"/>
            <jsp:setProperty property="name" name="obj" value="yeu va han"/>
            <jsp:setProperty property="price" name="obj" value="1000"/>
        </jsp:useBean>
        <%
            String msg="hello world";
        %>
        <h4>${1+1}</h4>
        <h4>${s}</h4>
        <h4>${obj.name}</h4>
        <h4>${obj.isExpensive()}</h4>
    </body>
</html>
