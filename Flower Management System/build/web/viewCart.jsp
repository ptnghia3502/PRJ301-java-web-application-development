<%-- 
    Document   : viewCart
    Created on : Jun 11, 2022, 1:29:07 PM
    Author     : Nghia
--%>

<%@page import="sample.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String name = (String) session.getAttribute("name");
                if (name != null) {
            %>
            <h3>Welcome <%= session.getAttribute("name")%> come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <h3><a href="personalPage.jsp">Personal page</a></h3>
            <%
                }
            %>
            <font style='color:red;'><%= (request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%> </font>

            <table width="100%" class="shopping">
                <tr><td>Product id</td><td>Price</td><td>Image</td><td>Quanity</td><td>Action</td></tr>
                <%
                    HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                    int total = 0;
                    if (cart != null && cart.size()!=0) {
                        //Set<String> pids = cart.keySet();
                        //for (String pid : pids) {
                        //    int quantity = cart.get(pid);
                            
                            for (String id : cart.keySet()) {
                                Plant p = PlantDAO.getPlant(Integer.parseInt(id));
                %>
                <form action="mainController" method="post">
                    <tr><td><input type="hidden" value="<%= id %>" name="pid"><a href="getPlantServlet?txtpid=<%= id %>"><%= id %></a></td> <!-- viewDetailProduct.jsp -->
                        <td><%= p.getPrice() %></td>
                        <td><img src='<%= p.getImgpath()%>' class='plantimg'/></td>
                        <td><input type="number" value="<%= cart.get(id) %>" name="quantity"></td>
                        <td><input type="submit" value="update" name="action">
                            <input type="submit" value="delete" name="action"></td>
                    </tr>
                </form>
                <%              total = total + cart.get(id)*p.getPrice();
                            }
                    }else {
                %>
                <tr><td>Your cart is empty</td></tr>
                <%  } %>
                <tr><td>Total money: <%= total %> </td></tr>
                <tr><td>Order Date: <%= (new Date()).toString()%></td></tr>
                <tr><td>Ship Date: N/A </td></tr>
            </table>
        </section>
        <section><form action="mainController" method="post">
                <input type="submit" value="saveOrder" name="action" class="submitorder">
            </form></section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
