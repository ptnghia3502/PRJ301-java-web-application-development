<%-- 
    Document   : searchOrder
    Created on : Jun 22, 2022, 10:10:45 AM
    Author     : Nghia
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
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
            <h1>History orders</h1>
            <%
                String from = request.getParameter("txtfrom");
                String to = request.getParameter("txtto");

                Date dateFrom = Date.valueOf(from);
                Date dateTo = Date.valueOf(to);

                ArrayList<Order> list;
                String[] status = {"", "processing", "completed", "canceled"};
                list = OrderDAO.getOrdersByDate(dateFrom, dateTo);
                
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {%>
            <div>
                <table class="order">
                    <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                    <tr><td><%= ord.getOrderID()%></td>
                        <td><%= ord.getOrderDate()%></td>
                        <td><%= ord.getShipDate()%></td>
                        <td><%= status[ord.getStatus()]%>
                            <br/><% if (ord.getStatus() == 1) {%><a href='orderstatusServlet?orderstatus=1&orderid=<%= ord.getOrderID()%>'>cancel order</a> <% }%>
                        </td>
                        <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID()%>">detail</a></td></tr>              
                </table>
            </div>
            <%     }
                } else {
                    out.println("Can not find order from " + dateFrom + " to " + dateTo);
                }
            %>      
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
