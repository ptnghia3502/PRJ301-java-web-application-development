<%-- 
    Document   : LogIn
    Created on : 22-Jun-2022, 18:13:12
    Author     : tucuo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
    </head>
    <body>
        <h1>LOG IN</h1>
        <form action="LoginController" method="POST">
            Username: <input type = "TEXT" name ="Username" value =""> <br>
            
            Password: <input type ="PASSWORD" name ="Password" value =""> <br>
            
            <input type ="SUBMIT" name ="Submit" value ="Submit">
            
            <input type = "RESET" name ="Reset">
        </form>
        <%  String fault;
            try{
          fault = request.getAttribute("fault_LogIn").toString();
        } 
        catch(NullPointerException e){
            fault = null;
        }
        if (fault != null){
            if (fault.equals("Username_Password_Incorrect")){
                    %>
                  
                        <h3 style="color:red;">
                        Incorrect Username or Password. Please try again.
                        </h3>
                                
                    <%
        }
}
        %>
     
                <form action="SignUpController" method="POST">
            <input type ="SUBMIT" name = "Sign Up" value ="Sign Up">
        </form>
    </body>
</html>
