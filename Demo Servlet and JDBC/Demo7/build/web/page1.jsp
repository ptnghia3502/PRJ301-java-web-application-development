<%-- 
    Document   : page1
    Created on : Jun 20, 2022, 4:25:24 PM
    Author     : Nghia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="num1" value="3" scope="page" />
        <c:set var="num2" value="${ num1%2}" />
        <c:out value="hello moi nguoi"/>
        <c:out value="${num1}"/>
        <c:out value="${num2}"/>
        <c:if test="${num1>num2}">
            <c:out value="num1 lon hon num2"/>
        </c:if>
        <hr>
        
        <h1>Decision Demo</h1>
        <form>
            Number <input type="text" name="num" value="${param.num}" /><br/>
            <c:choose>
                <c:when test="${empty param.num}">
                </c:when>
                <c:when test="${param.num%2 != 0}">
                    ${param.num} is an odd number.
                </c:when>
                    <c:when test="${param.num%2 == 0}">
                    ${param.num} is an even number.
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </form>
        <hr>
        
        <h1>Exception Demo</h1>
        <form>
            Num 1: <input type="text" name="num1" value="${param.num1}" /><br/>
            Num 2: <input type="text" name="num2" value="${param.num2}" /><br/>
            <c:catch var="ee">
                <c:if test="${not empty param.num1 and not empty param.num2}">
                    <c:set var="division" value="${param.num1 / param.num2}" />
                    Division: <c:out value="${division}" /><br/>
                </c:if><br/>
            </c:catch>
                
            <input type="submit" value="Divide" />
            <c:if test="${not empty ee}">
                Error occurred<br/>
                <c:out value="${ee}" /><br/>
            </c:if>
        </form>
    </body>
</html>
