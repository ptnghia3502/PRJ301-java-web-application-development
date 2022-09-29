<%@page import="java.util.ArrayList"%>
<%@page import="cuongnvt.Fruit.FruitDTO"%>
<%@page import="cuongnvt.Fruit.FruitDTO"%>
<%@page import="cuongnvt.Account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <% session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("account");
            String welcome, search;
            try{
                search = session.getAttribute("search").toString();
            }catch(NullPointerException ex){
                search ="";
            }
            try {
                welcome = account.getFullname();
            } catch (NullPointerException ex) {
                welcome = "";
            }
            ArrayList<FruitDTO> fruits = (ArrayList<FruitDTO>) session.getAttribute("fruits");
            if (fruits == null) {
                fruits = new ArrayList<>();
            }
        %>
        <h1>Welcome, <%= welcome%> </h1>
        <form action="DispatchServlet" method="POST">
            <input type ="submit" name ="btAction" value ="Log out">
        </form>
        <form action="DispatchServlet" method="POST">
            <input type = "text" name ="txtSearch" value ="<%=search%>">
            <input type ="submit" name ="btAction" value ="Search">
            <input type ="submit" name ="btAction" value ="Check my cart">
        </form>  

        Note: Type "all" if you want to display all fruits.<br>
        <%
            if (fruits.isEmpty()) {
        %> 
        <h1> No record matched.</h1>

        <%
        } else {
        %>   
        <table border="1">
            <thead>
                <tr>
                    <th> Number</th>
                    <th>ID</th>
                    <th>Name </th>
                    <th>Color</th>
                    <th>Description</th>
                    <th>Value</th>
                    <th>Quantity </th>
                    <th>Purchase </th>
                </tr>
            </thead>
            <tbody>

                <%
                    int count = 1;
                    for (FruitDTO fruit : fruits) {

                %> 
            <form method="POST" id="<%= fruit.getID()%>" action = "DispatchServlet"></form>
            <tr>
                <td><%= count++%></td>
                <td><%=fruit.getID()%>
                    <input type ="hidden" name = "fruitID" value ="<%=fruit.getID()%>" form = "<%= fruit.getID()%>" /> 
                </td>
                <td><%= fruit.getName()%></td>
                <td><%= fruit.getColor()%></td>
                <td><%= fruit.getDescription()%> </td>
                <td><%=fruit.getValue()%> VND/kg </td>
                <td>
                    <input type ="text" name ="purchaseNumber" value="" form = "<%= fruit.getID()%>">        
                </td>
                <td>    

                    <input type ="submit" name = "btAction" value = "Purchase" form = "<%= fruit.getID()%>"> 
                </td>
            </tr>
            <%
                }
            %>


        </tbody>
    </table>


    <%
        }
        String purchaseStatus;
        try {
            purchaseStatus = request.getAttribute("purchaseStatus").toString();
        } catch (NullPointerException ex) {
            purchaseStatus = "nothing";
        }

        if (purchaseStatus.equals("error")) {
    %>
    <h3> An unexpected error uccur. Please type a non-negative number in Quality field.</h3>
    <%
        }
        if (purchaseStatus.equals("success")) {
    %>
    <h3> Purchase successfully. Please check our your cart.</h3>
    <%
        }
    %>
</body>
</html>
