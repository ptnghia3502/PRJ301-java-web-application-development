<%-- 
    Document   : page2
    Created on : Jun 15, 2022, 4:29:25 PM
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
        <!-- lay data trong session ten obj -->
        <jsp:useBean id="obj" class="basic.Book" scope="session"/>
        <%
            out.println("obj:" + obj.getId() + "," + obj.getName());
        %>
        <%= obj.getId() %> <%= obj.getName() %>
        <jsp:getProperty property="id" name="obj" />
        <jsp:getProperty property="name" name="obj" />
        <hr/>
        <jsp:useBean id="b" class="basic.Book" scope="request"/>
        <h3><%= request.getParameter("counter") %></h3>
        <h3>${param.counter}</h3>
        <h3><jsp:getProperty name="b" property="name"/></h3>
    </body>
</html>
