<%-- 
    Document   : adminIndex
    Created on : Jun 3, 2022, 5:08:33 PM
    Author     : Nghia
--%>

<%@page import="dao.AccountDao"%>
<%@page import="basicobject.Account"%>
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
        <h3>Welcome, <%= ((Account)session.getAttribute("loginedUser")).getFullname() %> </h3>
        <%
            ArrayList<Account> list = AccountDao.getAccounts();
            if (list != null && list.size() > 0) {
        %>
        <table>
            <% for(Account a : list) { %>
            <form action="BlockAccountServlet" method="post">
                <input type="hidden" name="txtaccid" value="<%= a.getAccitd()%>"/>
                <input type="hidden" name="txtstatus" value="<%= a.getStatus()%>"/>
                <tr>
                    <td><%= a.getAccitd() %></td>
                    <td><%= a.getEmail()%></td>
                    <td><%= a.getFullname()%></td>
                    <td><%= a.getStatus() %></td>
                    <td><input type="submit" value="block/unblock"/></td>
                </tr>
            </form>
            <% } %>
        </table>
        <%
            } else {
                out.println("the list is empty");
            }
        %>
    </body>
</html>
