<%-- 
    Document   : registration
    Created on : Jun 1, 2022, 4:31:44 PM
    Author     : Nghia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <form action="mainController" method="post" class="formregister">
                <h1>Register</h1>
                <table>
                    <tr><td>email</td><td><input type="text" name="txtemail" required="" pattern="^(\\w)+@(a-zA-Z]+([.](\\w)+){1,2}"></td></tr>
                    <tr><td>full name</td><td><input type="text" name="txtfullname" required=""></td></tr>
                    <tr><td>password</td><td><input type="text" name="txtpassword" required=""></td></tr>
                    <tr><td>phone</td><td><input type="text" name="txtphone"></td></tr>
                    <tr><td colspan="2"><input type="submit" value="register" name="action"></td></tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</body>
</html>
