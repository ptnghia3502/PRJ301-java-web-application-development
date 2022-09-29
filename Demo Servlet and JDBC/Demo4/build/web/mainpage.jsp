<%-- 
    Document   : mainpage
    Created on : May 30, 2022, 4:21:58 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  errorPage="errorpage.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='mystyle.css' type='text/css'/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <section> other content 
            <%
                //co tinh tao exception de test
                int x = Integer.parseInt("ahihi");
            %>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
