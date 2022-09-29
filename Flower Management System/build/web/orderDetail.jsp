<%-- 
    Document   : personalPage
    Created on : Jun 1, 2022, 4:53:40 PM
    Author     : Nghia
--%>

<%@page import="sample.dto.OrderDetail"%>
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
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail detail : list) { %>
                        <table class="order">
                            <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Price</td><td>Quantity</td></tr>
                            <tr><td><%= detail.getOrderID() %></td><td><%= detail.getPlantID() %></td>
                                <td><%= detail.getPlantName() %></td>
                                <td><img src='<%= detail.getImgPath()%>'/></td><td><%= detail.getPrice()%></td>
                                <td><%= detail.getQuantity()%></td>
                                <% money = money + detail.getPrice() * detail.getQuantity(); %>
                            </tr>
                        </table>
            <%          }//endfor %>  
                        <h3> Total money: <%= money %></h3>
            <%      }//endif
                    else{
            %>
            <p>You don't have any order</p>
            <%      }//end else
                }//end if   
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>          
        </footer>
        <%}%>
    </body>
</html>
