
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
    </head>
    <body>
        <h1>Log in</h1>
        <form action="DispatchServlet" method="POST">
            Username: <input type ="text" name = "txtUsername" value =""> <br>
            Password: <input type ="password" name = "txtPassword" value =""> <br>
            <input type ="submit" name ="btAction" value ="Submit">
            <input type ="submit" name ="btAction" value ="Log out">
            <input type ="reset" name ="Reset">
        </form>
    </body>
</html>
