<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="header.jsp" />

<body>
<div class="container">
    <h2>Login Page</h2>
    <div id="login-form" >
        <form action="/login" method="post">
            <div class="form-group">
                <label for="username_login">Email:</label>
                <input type="text" class="form-control" id="username_login" name="username" required>
            </div>
            <div class="form-group">
                <label for="password_login">Password:</label>
                <input type="password" class="form-control" id="password_login" name="password" required>
            </div>
            <c:if test="${not empty displayMessage}">
                <div class="alert alert-danger">
                        ${displayMessage}
                </div>
            </c:if>
            <button type="submit" class="btn btn-default">Login</button>
        </form>
        <form action="/register" method="get">
            <button type="submit">I want to Register</button>
        </form>
    </div>
    <br>

</div>
</body>
<jsp:include page="footer.jsp" />
</html>