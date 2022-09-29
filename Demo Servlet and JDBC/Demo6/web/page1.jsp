<%-- 
    Document   : page1
    Created on : Jun 15, 2022, 4:19:57 PM
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
        <!-- tao 1 object book va luu no vao session neu trong session chua co object nay-->
        <jsp:useBean id="obj" class="basic.Book" scope="session">
            <jsp:setProperty property="id" name="obj" value="1"/>
            <jsp:setProperty property="name" name="obj" value="yeu va han"/>
            <jsp:setProperty property="price" name="obj" value="1000"/>
        </jsp:useBean>
        <a href="page2.jsp">page2</a>
        <jsp:include page="page3.jsp"/>
    </body>
</html>
