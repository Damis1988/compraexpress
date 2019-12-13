<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SMARTFOOD — Checkout</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
</head>
<body>

<header>
    <c:choose>
        <c:when test="${!guest}">
            <li><a href="<%=request.getContextPath()%>/account/secure/viewAccount">My Account</a></li>
            <li><a href="<%=request.getContextPath()%>/secure/restaurant/listRestaurant">Main Page</a></li>
            <li><a href="<%=request.getContextPath()%>/secure/order/view">Cart</a></li>
            <li><a href="<%=request.getContextPath()%>/login/secure/logout">Log out</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="<%=request.getContextPath()%>/secure/order/view">Cart</a></li>
        </c:otherwise>
    </c:choose>
</header>

<h2>Congratulations!</h2>
<br>
<h3>Your order number is <c:out value="${number}"/>.</h3>
<p>It should be ready in <c:out value="${minutes}"/> minutes.</p>

<button class="btn btn-primary" onclick="window.location.href ='<%=request.getContextPath()%>/secure/restaurant/listRestaurant';">Go back to the Main Page</button>

</body>
</html>
