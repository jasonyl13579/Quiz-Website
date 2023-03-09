<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../header.jsp" />
<head>


</head>>
<body>
<div class="container">
    <h1>User Management</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Last Name</th>
            <th>First Name</th>
            <th>User Name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.lastname}</td>
                <td>${user.firstname}</td>
                <td>${user.username}</td>
                <td><c:if test="${user.active}">
                    Activate
                    </c:if>
                    <c:if test="${not user.active}">
                        Suspend
                    </c:if></td>
                <td>
                    <form action="/admin/user-management/${user.user_id}/toggle-status/" method="post">
                        <button type="submit" class="btn btn-default">
                            <c:if test="${user.active}">
                                Suspend
                            </c:if>
                            <c:if test="${not user.active}">
                                Activate
                            </c:if>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<jsp:include page="../footer.jsp" />
</html>