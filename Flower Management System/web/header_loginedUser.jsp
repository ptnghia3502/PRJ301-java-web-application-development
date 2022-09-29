<%-- 
    Document   : header_loginedUser
    Created on : Jun 1, 2022, 4:50:38 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <nav>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="mainController?action=changeprofile">Change profile</a></li>
            <li><a href="mainController?action=completedorders">Completed orders</a></li>
            <li><a href="mainController?action=canceledorders">Canceled orders</a></li>
            <li><a href="mainController?action=preocessingorders">Processing orders</a></li>
            <li><form action="mainController?action=searchOrderByDate" method="post" class="formsearch">
                    from <input type="text" name="txtfrom"> to <input type="text" name="txtto"> 
                <input type="submit" value="search">
                </form></li>
        </nav>
    </body>
</html>
