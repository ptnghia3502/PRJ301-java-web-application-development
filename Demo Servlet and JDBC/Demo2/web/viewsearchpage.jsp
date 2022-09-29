<%-- 
    Document   : viewsearchpage
    Created on : May 27, 2022, 5:24:40 PM
    Author     : Nghia
--%>

<%@page import="basicobject.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
                        <td><a hrf="#">add to cart</a></td>
                    </tr>
                    <%}%>
                </table>
                <%
            }
        %>
    </body>
</html>
