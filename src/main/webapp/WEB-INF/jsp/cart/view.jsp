<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SMARTFOOD — Cart View</title>
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


    <h2>SHOPPING CART</h2>

    <c:choose>
        <c:when test="${cart.size() != 0}">
            <table>
                <tr>
                    <th colspan="4">CART PREVIEW</th>
                </tr>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="item" items="${cart}">
                    <tr>
                        <td><c:out value="${item.product.name}"/></td>
                        <td><c:out value="${item.quantity}"/></td>
                        <td><c:out value="${item.value}"/></td>
                    </tr>
                </c:forEach>
            </table>

            <button class="btn btn-primary" onclick="window.location.href ='<%=request.getContextPath()%>/secure/order/finalize';">Fechar Compra</button>
        </c:when>
        <c:otherwise>
            <p>Your cart is empty! Check for some <a href="<%=request.getContextPath()%>/secure/restaurant/listRestaurant">restaurants</a>.</p>
        </c:otherwise>
    </c:choose>

</body>
</html>
