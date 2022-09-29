<%@page import="cuongnvt.Validation.FaultBlock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sigh Up</title>
    </head>
    <body>
        <h1>SIGH UP</h1>
        <form action="SignUpController" method="POST">
            Username*:<input type = "TEXT" name = "Username" value = ""> (6-20 Characters) <br>
            Administrative Right*:<input type ="CHECKBOX" name ="Role" value = "CHECKED"> <br>
            Password*:<input type = "PASSWORD" name = "Password" value = ""> (6-20 Characters) <br>
            Password Confirm*:<input type = "PASSWORD" name = "PasswordConfirm" value = ""> <br>
            Full Name*:<input type = "TEXT" name = "FullName" value = ""> (2 - 40 Characters) <br>
            <input type ="SUBMIT" name = "Sign Up 2" value = "Sign Up">
            <input type ="RESET" name = "Reset">
        </form>
        <%  FaultBlock fb = (FaultBlock) request.getAttribute("FaultBlock");
            if (fb != null) {
        %>
        <h3 style="color:red;"> Unable to Sign Up. Reason </h3> 
        <%
            for (String x : fb.getList()) {
        %>
        <div style ="color:red;"> * <%=x%> </div>

        <%
                }
            }
        %>
        <form action = "LogIn.jsp" method = "POST">
            <input type ="SUBMIT" name ="Submit" value ="Back to Log In"> 
        </form>
    </body>
</html>
