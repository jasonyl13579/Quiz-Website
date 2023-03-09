<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <h2>Register Page</h2>
    <div id="register-form">
        <h3>Register</h3>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="form-group">
                <label for="firstname">First Name:</label>
                <input type="text" class="form-control" id="firstname" name="firstname">
            </div>
            <div class="form-group">
                <label for="lastname">Last Name:</label>
                <input type="text" class="form-control" id="lastname" name="lastname">
            </div>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">
                        ${errorMessage}
                </div>
            </c:if>
            <button type="submit" class="btn btn-default">Register</button>

        </form>

        <form action="/login" method="get">
            <button type="submit">I want to Login</button>
        </form>
    </div>
    <br>

</div>
</body>
<jsp:include page="footer.jsp" />
</html>