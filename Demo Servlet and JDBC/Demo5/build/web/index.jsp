<%-- 
    Document   : index
    Created on : Jun 3, 2022, 4:16:31 PM
    Author     : Nghia
--%>

<%@page import="basicobject.Plant"%>
<%@page import="basicobject.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String note = request.getParameter("note");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="mainController?action=viewcart">view cart</a>
        <form action="Servlet1" method="post">
            <input type="text" name="txt" value="<%= request.getParameter("txt") %>"/>
            <input type="submit" value="click" />
        </form>
        <hr>
        <form action="SearchServlet" method="post">
            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")!= null)? request.getParameter("txtsearch"):"" %>" />
            <input type="submit" value="search" />
        </form>
        
            <%
                String msg = (String)request.getAttribute("warning");
            %>
            <p><%= (msg!=null)?msg:"" %></p>
            
        <!-- display sau khi tim -->
        <%
            //lay list co trong request do SearchServlet day qua
            ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("ketqua");
            if(list==null || list.size()==0) {
        %>
                <h2>not found</h2>
        <%
            }
            else {
                %>
                <table>
                    <% for (Plant p : list) {%>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getName() %></td>
                        <td><img width="100px" src="<%= p.getImgpath() %>"/></td>
                        <td><a href="mainController?action=addtocart&id=<%= p.getId() %>">add to cart</a></td>
                    </tr>
                    <%}%>
                </table>
                <%
            }
        %>
        <!--Cho nay hien thi thong bao them gio hang thanh cong -->
    <% if(note != null) { %>
    <scipt>
        <p>Them thanh cong</p>
    </scipt>
    <% } %>
    </body>
</html>
