<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Restaurant</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
</head>
<body>

    <headrer>
        <c:choose>
            <c:when test="${!guest}">
                <li><a href="<%=request.getContextPath()%>/account/secure/view">My Account</a></li>
                <li><a href="<%=request.getContextPath()%>/secure/order/viewOrder">Cart</a></li>
                <li><a href="<%=request.getContextPath()%>/login/secure/logout">Log out</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<%=request.getContextPath()%>/secure/order/viewOrder">Cart</a></li>
            </c:otherwise>
        </c:choose>
    </headrer>

    <c:choose>
        <c:when test="${all == null}">
            <p>There are no restaurants available.</p>
        </c:when>
        <c:otherwise>
            <h1>Choose a Restaurant</h1>
            <br>
            <c:forEach var="restaurant" items="${all}">
                <form method="post" action="<%=request.getContextPath()%>/secure/restaurant/menu">
                    <input type="hidden" name="id" value="${restaurant.getId()}" required>
                    <button type="submit" class="btn btn-primary"><c:out value="${restaurant.tradeName}"/></button>
                </form>
                <br><br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

</body>
</html>
