<%-- 
    Document   : viewcart
    Created on : Jun 10, 2022, 4:12:28 PM
    Author     : Nghia
--%>

<%@page import="dao.PlantDao"%>
<%@page import="basicobject.Plant"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function warning() {
                var tmp = window.comfirm("Ban co muon xoa mat hang nay?");
                if (tmp === true) {
                    return true;
                }
                return false;
            }
        </script>
    </head>
    <body>
        <%
            //lay cart trong session memory
            HashMap<String,Integer> cart = (HashMap) session.getAttribute("cart");
            if(cart==null || cart.size()==0) {
                out.println("Your cart is empty");
            }
            else {
                %>
                <table>
                    <tr><th>id</th>
                        <th>name</th>
                        <th>price</th>
                        <th>quantity</th>
                    </tr>
                    <%  int total=0;
                        for(String id: cart.keySet()) { 
                            Plant p = PlantDao.getPlant(Integer.parseInt(id)); 
                    %>
                    <form action="mainController" method="post">
                        <input type="hidden" value="<%= id %>" name="txtid"/>
                    <tr>
                        <td><%= id %></td>
                        <td><%= p.getName() %></td>
                        <td><%= p.getPrice() %></td>
                        <td><input type="number" value="<%= cart.get(id) %>" min="1" max="10" name="txtquantity"/></td>
                        <td><input type="submit" value="remove" name="action" onclick="return warning()"/>
                            <input type="submit" value="update" name="action"/>
                        </td>
                    </tr>
                    <% 
                       total = total + cart.get(id)*p.getPrice();
                            } 
                    %>
                </table>
                    </form>
                <%
                    out.println("<p>Total:" + total + "</p>");
                    Date d = new Date();
                    out.println("<p>order date:" + d.toString() + "</p>");
                    out.println("<p>shipping date: du kien trong 7 ngay </p>");
            }
        %>
        <a href="mainController?action=checkoutOrder">Hoan thanh don hang</a>
    </body>
</html>
