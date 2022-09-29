<%-- 
    Document   : loginPage
    Created on : Jun 6, 2022, 5:03:10 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="mainController" method="post">
            <input type="text" name="txtemail" /><br/>
            <input type="password" name="txtpassword" /><br/>
            <input type="submit" value="login" name="action" />
            
        </form>
    </body>
</html>
