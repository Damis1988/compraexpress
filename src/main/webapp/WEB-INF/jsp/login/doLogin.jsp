<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login to Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="width: 30em">
        <h2>LOGIN</h2>

        <c:if test="${message != null}">
            <br>
            <h3>
                <c:out value="${message}" />
            </h3>
            <br>
        </c:if>

        <form method="post" action="<%=request.getContextPath()%>/login/doLogin" class="form-horizontal">
            <div class="form-group form-group-lg">
                <div class="col-sm-10">
                    <input type="email" name="email" id="email" placeholder="e-mail"  class="form-control" required>
                    <input type="password" name="password" id="password" placeholder="password"  class="form-control" required>
                </div>
            </div>
            <div style="float:left">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
            <br><br>
            <div align="left">
                <a href="<%=request.getContextPath()%>/user/create">Create your account</a>
            </div>
        </form>
    </div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>
</body>
</html>
