
<%@page import="cuongnvt.Cart.CartDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cuongnvt.Fruit.FruitDTO"%>
<%@page import="cuongnvt.Fruit.FruitDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My cart</title>
    </head>

    <body>
        <%
            ArrayList<CartDTO> carts = (ArrayList<CartDTO>) session.getAttribute("carts");
            if (carts == null) {
                carts = new ArrayList<>();
            }
        %>
        <h1>My cart</h1>
        <form action="DispatchServlet" method="POST">
            <input type ="submit" value ="Back" name ="btAction">   
        </form>  
        <%
            if (carts.isEmpty()) {
        %>
        <h1> There is nothing in your cart. </h1>


        <% } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Number</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Color</th>
                    <th>Description</th>
                    <th>Value</th>
                    <th>Quantity </th>
                    <th>Total </th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>


                <%
                    int count = 1;
                    for (CartDTO cart : carts) {
                %>
                <tr>                
                    <td> <%= count++%> </td>
                    <td> <%= cart.getFruit().getID()%> </td>
                    <td><%= cart.getFruit().getName()%></td>
                    <td><%= cart.getFruit().getColor()%></td>
                    <td><%= cart.getFruit().getDescription()%></td>
                    <td><%= cart.getFruit().getValue()%> VND/Kg</td>
                    <td><%= cart.getQuantity()%></td>
                    <td><%= cart.getQuantity() * cart.getFruit().getValue()%> VND </td>
                    <td>
                        <form action = "DispatchServlet" method = "POST">
                            <input type ="hidden" value ="<%=cart.getFruit().getID()%>"  name ="fruitID">
                            <input type ='submit' value="Remove" name = "btAction">
                        </form>
                    </td>
                </tr>

                <%
                    }
                %>
                <tr> 
                    <td>Total</td>
                    <td></td>
                    <td></td> 
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                          <%
                            int total = 0;
                            for (CartDTO cart : carts) {
                           total += cart.getFruit().getValue() * cart.getQuantity();
                            }
                        %>
                      <%=total%> VND

                        <% %>
                    </td>  
                    <td> 
                      
                    </td>  
                </tr>
            </tbody>
        </table>

        <%
            }
        %>
    
        <form action="DispatchServlet" method="POST">
            <input type ="submit" value ="Pay" name ="btAction">   
        </form>    
    </body>
    
</html>
