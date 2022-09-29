<%-- 
    Document   : page4
    Created on : Jun 15, 2022, 4:51:15 PM
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
        <jsp:useBean id="b" class="basic.Book" scope="request">
            <jsp:setProperty name="b" property="id" value="1"/>
            <jsp:setProperty name="b" property="name" value="truyen kieu"/>
        </jsp:useBean>
        <jsp:forward page="page2.jsp">
            <jsp:param name="counter" value="100"/>
        </jsp:forward>
    </body>
</html>
