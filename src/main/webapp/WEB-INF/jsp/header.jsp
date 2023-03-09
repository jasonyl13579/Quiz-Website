<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<c:if test="${not empty sessionScope.user}">
    <%-- User is logged in --%>
    <c:set var="is_login" value="true" />
    <c:set var="open_quiz" value="${sessionScope.user.open_quiz_id}" />
    <c:set var="is_admin" value= "${sessionScope.user.admin}" />
</c:if>

<c:if test="${empty sessionScope.user}">
    <%-- User is not logged in --%>
    <c:set var="is_login" value="false" />
    <c:set var="is_admin" value= "false" />
</c:if>

<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            /* Center login form */
            .container {
                max-width: 400px;
                margin: 20px auto auto;
            }
            table {
                padding-top: 1rem;
            }
            table td, .table th {
                max-width: 400px;
                padding: 2rem;
            }
        </style>
</head>
<body>
<!-- Navigation bar -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/login">Quiz</a>
        </div>
        <ul class="nav navbar-nav">
            <c:if test="${is_admin}">
                <li><a href="/admin">Admin</a></li>
            </c:if>
            <c:if test="${is_login}">
                <li><a href="/home">Home</a></li>
                <li><a href="/logout">Logout</a></li>
            </c:if>
            <c:if test="${open_quiz != null}">
                <li><a href="/quiz">Open quiz</a></li>
            </c:if>
            <c:if test="${not is_login}">
                <li><a href="/register">Register</a></li>
                <li><a href="/login">Login</a></li>
            </c:if>
            <li><a href="/contact">Contact us</a></li>
        </ul>
    </div>
</nav>
</body>

</html>