<%-- 
    Document   : ManagePlants
    Created on : Jun 24, 2022, 12:20:16 AM
    Author     : Nghia
--%>

<%@page import="sample.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <form action="mainController" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"": request.getParameter("txtsearch")%>">
                            <select name="searchby"><option value="byname">by name</option>
                                <option value="bycate">by cate</option>
                            </select>
                            <input type="submit" value="searchPlant" name="action">
                        </form>
        <h1></h1>

        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String [] tmp = {"out of stock","available"};
                if (keyword==null && searchby==null)
                    list = PlantDAO.getPlants("", "");
                else
                    list = PlantDAO.getPlants(keyword, searchby);
                if (list != null && !list.isEmpty()) {
                        for(Plant p : list) { %>
                        <div class="col-md-4">
                        <table class='producttable'>
                            <tr>
                                <td><img src='<%= p.getImgpath()%>' class='plantimg'/></td>
                                <td>Product ID: <%= p.getId() %></td>
                                <td>Product name: <%= p.getName() %></td>
                                <td>Price: <%= p.getPrice() %></td>
                                <td>Status: <%= tmp[p.getStatus()] %></td>
                                <td>Category: <%= p.getCatename() %></td>
                                
                            </tr>
                        </table>
                        </div>
            <%      }
                }
            %>            
        </section>
    </body>
</html>

