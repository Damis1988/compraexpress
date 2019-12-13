<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SMARTFOOD — Edit Account</title>
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
            <li><a href="<%=request.getContextPath()%>/secure/restaurant/listRestaurant">Main Page</a></li>
            <li><a href="<%=request.getContextPath()%>/secure/order/view">Cart</a></li>
            <li><a href="<%=request.getContextPath()%>/login/secure/logout">Log out</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="<%=request.getContextPath()%>/secure/order/view">Cart</a></li>
        </c:otherwise>
    </c:choose>
</headrer>


<div class="container">
    <h2>EDIT YOUR ACCOUNT</h2>

            <c:if test="${message != null}">
                <h3>
                    <c:out value="${message}" />
                </h3>
            </c:if>

    <br>
    <h2>Personal information:</h2>
    <form method="post" action="<%=request.getContextPath()%>/account/secure/update" class="form-horizontal">
        <input type="hidden" name="id" value="${customer.id}">
        <div class="form-group form-group-lg">
            <label for="name" class="col-sm-2 control-label">Name:</label>
            <div class="col-sm-10">
                <input type="text" name="name" id="name" class="form-control" pattern="[a-z\s]+$"
                       title="Enter letters only" value="${customer.name}" required>
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label for="surname" class="col-sm-2 control-label">surname:</label>
            <div class="col-sm-10">
                <input type="text" id="surname" class="form-control" name="surname" pattern="[a-z\s]+$"
                       title="Enter letters only" value="${customer.surname}" required>
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label for="cpf" class="col-sm-2 control-label">CPF:</label>
            <div class="col-sm-10">
                <input type="text" id="cpf" class="form-control" name="cpf" onkeypress="$(this).mask('000.000.000-00');" value="${customer.cpf}" required>
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label for="password" class="col-sm-2 control-label">Password:</label>
            <div class="col-sm-10">
                <input type="password" id="password" class="form-control" name="password" pattern="[0-9a-fA-F]{4,8}" inputmode="numeric" required
                       title="Digite um password consistindo de 4-8 dígitos hexadecimais">
            </div>
        </div>

        <c:if test="${customer.address != null}">
            <h2>Address:</h2>

            <div class="form-group form-group-lg">
                <label for="street" class="col-sm-2 control-label">Street:</label>
                <div class="col-sm-10">
                    <input type="text" id="street" class="form-control" name="street" pattern="[a-z\s]+$"
                           title="Enter letters only" value="${customer.address.street}">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="number" class="col-sm-2 control-label">Number:</label>
                <div class="col-sm-10">
                    <input type="number" id="number" class="form-control" name="number" value="${customer.address.number}">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="complement" class="col-sm-2 control-label">Complement:</label>
                <div class="col-sm-10">
                    <input type="text" id="complement" class="form-control" name="complement" value="${customer.address.complement}">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="zip" class="col-sm-2 control-label">Zip code:</label>
                <div class="col-sm-10">
                    <input type="number" id="zip" class="form-control" name="zip" onkeypress="$(this).mask('00000-000')" value="${customer.address.number}">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="neighborhood" class="col-sm-2 control-label">Neighborhood:</label>
                <div class="col-sm-10">
                    <input type="text" id="neighborhood" class="form-control" name="neighborhood" pattern="[a-z\s]+$"
                           title="Enter letters only" value="${customer.address.neighborhood}">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="city" class="col-sm-2 control-label">City:</label>
                <div class="col-sm-10">
                    <input type="text" id="city" class="form-control" name="city" pattern="[a-z\s]+$"
                           title="Enter letters only" value="${customer.address.city}">
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="country" class="col-sm-2 control-label">Country:</label>
                <div class="col-sm-10">
                    <input type="text" id="country" class="form-control" name="country" pattern="[a-z\s]+$"
                           title="Enter letters only" value="${customer.address.country}">
                </div>
            </div>
        </c:if>

        <h2>Personal and contact information:</h2>

        <c:if test="${customer.phone != null}">
            <div class="form-group form-group-lg">
                <label for="phone" class="col-sm-2 control-label">phone:</label>
                <div class="col-sm-10">
                    <input type="text" id="phone" name="phone" class="form-control" onkeypress="$(this).mask('(00) 0000-00009')" value="${customer.phone}">
                </div>
            </div>
        </c:if>
        <div class="form-group form-group-lg">
            <label for="email" class="col-sm-2 control-label">E-mail:</label>
            <div class="col-sm-10">
                <input type="email" id="email" class="form-control" name="email" value="${customer.email}">
            </div>
        </div>

        <div style="float:right">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>

<br><br>
</body>
</html>