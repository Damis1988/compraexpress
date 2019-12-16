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
                <li><a href="<%=request.getContextPath()%>/account/update">My Account</a></li>
                <li><a href="<%=request.getContextPath()%>/restaurant/listRestaurant">Main Page</a></li>
                <li><a href="<%=request.getContextPath()%>/order/view">Cart</a></li>
                <li><a href="<%=request.getContextPath()%>/login/logout">Log out</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="<%=request.getContextPath()%>/order/view">Cart</a></li>
            </c:otherwise>
        </c:choose>
    </headrer>
    <br><br><br><br>

    <c:if test="${cart != null && cart.size() != 0}">
        <table>
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
                    <form id="cartpreview" method="post" action="<%=request.getContextPath()%>/order/updateItemQty" class="form-horizontal">
                        <div>
                            <input type="hidden" name="restaurant" value="${restaurant.id}">
                            <input type="hidden" name="product" value="${item.product.id}">
                            <input type="number" step="1" class="form-control" name="qty" value="${item.quantity}">
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

        <button class="btn btn-primary" onclick="window.location.href ='<%=request.getContextPath()%>/order/view';">Fechar Compra</button>
    </c:if>


    <h2><b>MENU</b></h2>
    <br><br>
    <c:forEach var="sector" items="${sectors}">
        <h3 style="text-transform:uppercase">${sector.name}</h3>
        <br><br>
        <c:forEach var="product" items="${products}">
            <c:if test="${product.sector.equals(sector)}">
                <br><br>
                <div>
                    <p style="text-transform:uppercase" align="left">${product.name} — ${product.price}</p>
                    <br><br>
                    <form method="post" action="<%=request.getContextPath()%>/order/addItem" class="form-horizontal">
                        <div class="col-sm-10">
                            <input type="hidden" name="restaurant" value="${restaurant.id}">
                            <input type="hidden" name="product" value="${product.id}">
                            <label for="qty">Qty: </label>
                            <input type="number" step="1" class="form-control" id="qty" name="qty" value="0">
                        </div>
                        <div style="float:left">
                            <button type="submit" class="btn btn-primary">Update quantity</button>
                        </div>
                     </form>
                </div>
                <br><br>
            </c:if>
        </c:forEach>
        <br><br>
    </c:forEach>
</body>
</html>
