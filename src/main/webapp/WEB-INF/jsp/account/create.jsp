<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
</head>
<body>
    <div class="container" style="width: 30em">
        <h2>CREATE ACCOUNT</h2>

        <c:if test="${message != null}">
            <h4>
                <c:out value="${message}" />
                <c:if test="${success == true}">
                    <br><br>
                    <a href="<%=request.getContextPath()%>/login/login">Login here</a>
                </c:if>
            </h4>
        </c:if>

        <input type="text" value="${onGoing.name}">

        <!--
        <c:if test="${success == false}">
            <form method="post" action="<%=request.getContextPath()%>/account/create" class="form-horizontal">
                <div class="form-group form-group-lg">
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" class="form-control" placeholder="Name">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" id="surname" class="form-control" name="surname" placeholder="Surname">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" id="address" class="form-control" name="address" placeholder="Address">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" id="phone" name="phone" class="form-control" onkeypress="$(this).mask('(00) 0000-00009')" placeholder="Phone Number">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" id="cpf" class="form-control" name="cpf" onkeypress="$(this).mask('000.000.000-00');" placeholder="CPF">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" id="email" class="form-control" name="email" placeholder="Email">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" id="password" class="form-control" name="password" placeholder="Password">
                    </div>
                </div>
                <div style="float:right">
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </c:if>
        -->
    </div>
    <br><br>
    <a href="<%=request.getContextPath()%>/secure">Main Page</a>
</body>
</html>