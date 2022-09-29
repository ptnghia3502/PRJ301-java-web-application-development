<%-- 
    Document   : personalPage
    Created on : Jun 1, 2022, 4:53:40 PM
    Author     : Nghia
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDAO"%>
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
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c)
                    if (aCookie.getName().equals("elector")) {
                        token = aCookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
            }
            else
                login = true;
            
            //show content if login = true
            if (login) {
        %>
     <!--   <p><font color='red'>you must login to view personal page</font></p>
        <p></p> -->
        <% // } else {
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
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {%>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/><% if (ord.getStatus() == 1) { %><a href='orderstatusServlet?orderstatus=1&orderid=<%= ord.getOrderID() %>'>cancel order</a> <% } %>
                    </td>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td></tr>              
            </table>
            <%      }
                }
                else {
            %>   
            <p>You don't have any order</p>
            <%      }//end else
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>          
        </footer>
        <%}%>
    </body>
</html>
