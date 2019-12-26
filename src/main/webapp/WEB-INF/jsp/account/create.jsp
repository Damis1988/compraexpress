<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import ="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SMARTFOOD — Sign up</title>
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
                    <a href="<%=request.getContextPath()%>/login/doLogin">Login here</a>
                </c:if>
            </h4>
        </c:if>

        <c:if test="${success == false}">

            <form method="post" action="<%=request.getContextPath()%>/account/create" class="form-horizontal">
                <h3>Personal information:</h3>
                <br>
                <div class="form-group form-group-lg">
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.name != null)}">
                                <input type="text" name="name" id="name" class="form-control" value="${onGoing.name}" required>
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="name" id="name" class="form-control" placeholder="Name" required>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.surname != null)}">
                                <input type="text" name="surname" id="surname" class="form-control" value="${onGoing.surname}" required>
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="surname" class="form-control" name="surname" placeholder="Surname" required>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.cpf != null)}">
                                <input type="text" id="cpf" class="form-control" name="cpf" onkeypress="$(this).mask('000.000.000-00');" value="${onGoing.cpf}" required>
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="cpf" class="form-control" name="cpf" onkeypress="$(this).mask('000.000.000-00');" placeholder="CPF" required>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.email != null)}">
                                <input type="email" id="email" class="form-control" name="email" value="${onGoing.email}" required>
                            </c:when>
                            <c:otherwise>
                                <input type="email" id="email" class="form-control" name="email" placeholder="Email" required>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <input type="password" id="password" class="form-control" name="password" placeholder="Password" required>
                    </div>
                    <div class="col-sm-10">
                        <input type="password" id="confirm_password" class="form-control" name="confirm_password" placeholder="Confirm Password" required>
                    </div>
                    <br>
                    <h3>Contact information:</h3>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.phone != null)}">
                                <input type="text" id="phone" name="phone" class="form-control" onkeypress="$(this).mask('(00) 0000-00009')" value="${onGoing.phone}" >
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="phone" name="phone" class="form-control" onkeypress="$(this).mask('(00) 0000-00009')" placeholder="Phone Number" >
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.street != null)}">
                                <input type="text" id="street" class="form-control" name="street" pattern="[a-z\s]+$"
                                       title="Enter letters only" value="${onGoing.address.street}" >
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="street" class="form-control" name="street" pattern="[a-z\s]+$"
                                       title="Enter letters only" placeholder="Street" >
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.number != null)}">
                                <input type="number" id="number" class="form-control" name="number" step="1" value="${onGoing.address.number}" >
                            </c:when>
                            <c:otherwise>
                                <input type="number" id="number" class="form-control" name="number" step="1" placeholder="Number" >
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.complement != null)}">
                                <input type="text" id="complement" class="form-control" name="complement" value="${onGoing.address.complement}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="complement" class="form-control" name="complement" placeholder="Complement">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.zip != null)}">
                                <input type="number" id="zip" class="form-control" name="zip" onkeypress="$(this).mask('00000-000')"  value="${onGoing.address.zip}">
                            </c:when>
                            <c:otherwise>
                                <input type="number" id="zip" class="form-control" name="zip" onkeypress="$(this).mask('00000-000')"  placeholder="ZIP code">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.neighborhood != null)}">
                                <input type="text" id="neighborhood" class="form-control" name="neighborhood" pattern="[a-z\s]+$"
                                       title="Enter letters only" value="${onGoing.address.neighborhood}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="neighborhood" class="form-control" name="neighborhood" pattern="[a-z\s]+$"
                                       title="Enter letters only" placeholder="Neighborhood">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.city != null)}">
                                <input type="text" id="city" class="form-control" name="city" pattern="[a-z\s]+$"
                                       title="Enter letters only" value="${onGoing.address.city}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="city" class="form-control" name="city" pattern="[a-z\s]+$"
                                       title="Enter letters only" placeholder="City">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.state != null)}">
                                <input type="text" id="state" class="form-control" name="state" pattern="[a-z\s]+$"
                                       title="Enter letters only" value="${onGoing.address.state}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="state" class="form-control" name="state" pattern="[a-z\s]+$"
                                       title="Enter letters only" placeholder="State">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-10">
                        <c:choose>
                            <c:when test="${(onGoing != null) && (onGoing.address.country != null)}">
                                <input type="text" id="country" class="form-control" name="country" pattern="[a-z\s]+$"
                                       title="Enter letters only" value="${onGoing.address.country}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="country" class="form-control" name="country" pattern="[a-z\s]+$"
                                       title="Enter letters only" placeholder="Country">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div style="float:right">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
                <p>Already have an account? <a href="<%=request.getContextPath()%>/login/doLogin">Login Here!</a></p>
                <p>You are extra hungry? Try the <a href="<%=request.getContextPath()%>/login/guest">Express Checkout</a>.</p>
            </form>
        </c:if>
    </div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>
</body>
</html>