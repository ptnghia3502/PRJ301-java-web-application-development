<%-- 
    Document   : page2
    Created on : May 27, 2022, 5:01:01 PM
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
            //tao data count=1 luu trong request
            request.setAttribute("count", 1);
            //goi trang page3.jsp va truyen request cua page 2 vao page 3
            request.getRequestDispatcher("page3.jsp").forward(request, response);
        %>
        <a href="page3.jsp">goi page3.jsp</a>
    </body>
</html>
