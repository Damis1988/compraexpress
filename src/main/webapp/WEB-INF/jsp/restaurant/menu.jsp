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

    <c:if test="${message != null}">
        <c:out value="${message}"/>
    </c:if>

    <c:if test="${cart.size() != 0 && cart != null}">
        <br><br>
        <table style="text-align: center">
            <tr>
                <th colspan="4">CART PREVIEW</th>
            </tr>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>

        <c:forEach var="item" items="${cart}">
            <tr>
                <td><c:out value="${item.product.name}"/></td>
                <td><c:out value="${item.quantity}"/></td>
                <td><c:out value="${item.value}"/></td>
                <td>
                    <form id="cartpreview" method="post" action="<%=request.getContextPath()%>/secure/order/addItem" class="form-horizontal">
                        <div>
                            <input type="hidden" name="restaurant" value="${restaurant.id}">
                            <input type="hidden" name="product" value="${item.product.id}">
                            <input type="number" step="1" min="0" class="form-control" name="qty" value="${item.quantity}">
                        </div>
                    </form>
                </td>
                <td>
                    <div style="float:right">
                        <button form="cartpreview" type="submit" class="btn btn-primary">Update quantity</button>
                    </div>
                </td>
            </tr>
        </c:forEach>

        </table>

        <button class="btn btn-primary" onclick="window.location.href ='<%=request.getContextPath()%>/secure/order/viewOrder';">Fechar Compra</button>
    </c:if>

    <br><br>
    <h2><b>MENU</b></h2>
    <c:forEach var="sector" items="${sectors}">
        <br><br>
        <h3 style="text-transform:uppercase">${sector.name}</h3>
        <c:forEach var="product" items="${products}">
            <c:if test="${product.sector.id.equals(sector.id)}">
                <br><br>
                <div>
                    <p style="text-transform:uppercase" align="left">${product.name} — ${product.price}</p>
                    <br><br>
                    <form method="post" action="<%=request.getContextPath()%>/secure/order/addItem" class="form-horizontal">
                        <div class="col-sm-10">
                            <input type="hidden" name="restaurant" value="${restaurant.id}">
                            <input type="hidden" name="product" value="${product.id}">
                            <label for="qty">Qty: </label>
                            <input type="number" step="1" min="0" class="form-control" id="qty" name="qty" value="0">
                        </div>
                        <div style="float:right">
                            <button type="submit" class="btn btn-primary">Update quantity</button>
                        </div>
                    </form>
                </div>
                <br><br>
            </c:if>
        </c:forEach>
    </c:forEach>
</body>
</html>
