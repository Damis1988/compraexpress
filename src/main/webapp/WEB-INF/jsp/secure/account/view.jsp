<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account Information</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
</head>
<body>

<headrer>
    <c:choose>
        <c:when test="${!guest}">
            <li><a href="<%=request.getContextPath()%>/account/secure/view">My Account</a></li>
            <li><a href="<%=request.getContextPath()%>/secure/restaurant/listRestaurant">Main Page</a></li>
            <li><a href="<%=request.getContextPath()%>/secure/order/viewOrder">Cart</a></li>
            <li><a href="<%=request.getContextPath()%>/login/secure/logout">Log out</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="<%=request.getContextPath()%>/secure/order/viewOrder">Cart</a></li>
        </c:otherwise>
    </c:choose>
</headrer>

<div class="container" style="width: 30em">
    <h2>ACCOUNT INFORMATION</h2>
    <br><br><br>
    <h3>PERSONAL INFO</h3>
    <br><br>
    <p>Name: ${customer.name}</p>
    <p>Surname: ${customer.surname}</p>
    <p>CPF: ${customer.cpf}</p>
    <p>Email: ${customer.email}</p>
    <br><br><br>
    <c:if test="${customer.phone != null || customer.address != null}">
        <h3>CONTACT INFO</h3>
        <br><br>
        <c:if test="${customer.phone != null}">
           <p>Phone: ${customer.phone}</p><br>
        </c:if>
        <c:if test="${customer.address != null}">
            <p>Address: </p>
            <p>${customer.address.street}, ${customer.address.number}, ${customer.address.complement}</p>
            <p>${customer.address.zip} — ${customer.address.neighborhood}, ${customer.address.city}, ${customer.address.state}</p>
            <p>${customer.address.country}</p>
        </c:if>
    </c:if>
</div>
<br><br>
<a href="<%=request.getContextPath()%>/account/secure/update">Update</a>
<br><br>
<a href="<%=request.getContextPath()%>/account/secure/delete">Deletar</a>
<br><br>
<a href="<%=request.getContextPath()%>/secure">Main Page</a>
</body>
</html>