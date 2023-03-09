<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../header.jsp" />
<head>

</head>>
<body>
<div class="container">
    <h2>Admin Dashboard</h2>

    <div class="option-btns">
        <form action="/admin/user-management" method="get">
            <button class="btn btn-default">User Management</button>
        </form>
        <form action="/admin/quiz-result-management" method="get">
            <button class="btn btn-default">Quiz Result Management</button>
        </form>
        <form action="/admin/question-management" method="get">
            <button class="btn btn-default">Question Management</button>
        </form>
        <form action="/admin/contact-us-management" method="get">
            <button class="btn btn-default">Contact Us Management</button>
        </form>
    </div>
<%--        <div class="container-fluid mt-5">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-6 mx-auto">--%>
<%--                    <div class="list-group">--%>
<%--                        <a href="/admin/user-management" class="list-group-item list-group-item-action">User Management</a>--%>
<%--                        <a href="/admin/quiz-result-management" class="list-group-item list-group-item-action">Quiz Result Management</a>--%>
<%--                        <a href="/admin/question-management" class="list-group-item list-group-item-action">Question Management</a>--%>
<%--                        <a href="/admin/contact-us-management" class="list-group-item list-group-item-action">Contact Us Management</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
</div>
</body>
<jsp:include page="../footer.jsp" />
</html>