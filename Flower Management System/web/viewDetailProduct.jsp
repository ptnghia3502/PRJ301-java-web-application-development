<%-- 
    Document   : viewDetailProduct
    Created on : Jun 18, 2022, 10:22:14 PM
    Author     : Nghia
--%>

<%@page import="sample.dto.Plant"%>
<%@page import="sample.dao.PlantDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <%
                String pid = request.getParameter("txtpid");
                Plant p = PlantDAO.getPlant(Integer.parseInt(pid));
            %>
            <h2>Thong tin chi tiet san pham!</h2>
            <table class='order'>
                <tr><td>PID</td><td>Name</td><td>Price</td><td>Image</td><td>Description</td></tr>
                <tr><td><%= p.getId()%></td>
                    <td><%= p.getName()%></td>
                    <td><%= p.getPrice()%></td>
                    <td><img src='<%= p.getImgpath()%>' class='plantimg'/></td>
                    <td><%= p.getDescription()%></td>
                </tr>
            </table>
        </section>
        <footer>
            <%@include file="footer.jsp" %>          
        </footer>
    </body>
</html>
