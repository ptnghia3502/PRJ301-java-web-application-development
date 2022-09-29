<%-- 
    Document   : index
    Created on : Jun 1, 2022, 4:20:07 PM
    Author     : Nghia
--%>

<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String [] tmp = {"out of stock","available"};
                if (keyword==null && searchby==null)
                    list = PlantDAO.getPlants("", "");
                else
                    list = PlantDAO.getPlants(keyword, searchby);
                if (list != null && !list.isEmpty()) {
                        for(Plant p : list) { %>
                        <div class="col-md-4">
                        <table class='producttable'>
                            <tr>
                                <td><img src='<%= p.getImgpath()%>' class='plantimg'/></td>
                                <td>Product ID: <%= p.getId() %></td>
                                <td>Product name: <%= p.getName() %></td>
                                <td>Price: <%= p.getPrice() %></td>
                                <td>Status: <%= tmp[p.getStatus()] %></td>
                                <td>Category: <%= p.getCatename() %></td>
                                <td><a href="mainController?action=addtocart&pid=<%= p.getId() %>">add to cart</a></td>
                            </tr>
                        </table>
                        </div>
            <%      }
                }
            %>            
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
