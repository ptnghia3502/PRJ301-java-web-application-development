<%-- 
    Document   : processingOrders.jsp
    Created on : Jun 8, 2022, 8:25:39 PM
    Author     : Nghia
--%>

<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p><font color='red'>you must login to view personal page</font></p>
        <p></p>
        <%  } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back </h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section> <!--Load all orders of the user at here--> 
            <%
                String email = (String) session.getAttribute("email");
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                int orderList=0;
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {
                        if (ord.getStatus() == 1) { %>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/><% if (ord.getStatus() == 1)%><a href='orderstatusServlet?orderstatus=1&orderid=<%= ord.getOrderID() %>'>cancel order</a>
                    </td>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td></tr>      
            </table>
            <%          orderList++;
                        }
                    }
                }
                if(orderList==0) {
            %>    
            <p>You don't have any order</p>
            <%     
                }//end else
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>          
        </footer>
        <% } %>
    </body>
</html>