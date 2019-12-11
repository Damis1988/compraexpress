<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Restaurant Menu</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
</head>
<body>
    <h2><b>MENU</b></h2>
    <br><br>
    <c:forEach var="sector" items="${restaurant.menu.sectors}">
        <h3 style="text-transform:uppercase">${sector}</h3>
        <br><br>
        <c:forEach var="product" items="${restaurant.menu.productSet}">
            <c:if test="${product.sector.equals(sector)}">
                <p style="text-transform:uppercase">${product.name} — ${product.price} </p>
                <br>
                <p>${product.description}</p>
                <br><br>
                <form>
                    <div class="col-sm-10">
                        <input type="hidden" name="menu" value="${restaurant.id}">
                        <input type="hidden" name="product" value="">
                        <input type="number" step="1" class="form-control" name="qty" placeholder="quantity">
                    </div>
                    <div style="float:right">
                        <button type="submit" class="btn btn-primary">Include</button>
                    </div>
                </form>
            </c:if>
        </c:forEach>
    </c:forEach>
</body>
</html>
