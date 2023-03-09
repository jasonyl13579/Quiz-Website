<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Result</title>
</head>

<body>
<jsp:include page="../header.jsp" />
<div class="container">
    <h1>Quiz Result Management Page</h1>
    <br>
    <form class="form-inline"  action="/admin/quiz-result-management" method="post">
        <div class="form-group">
            <label for="category">Filter by Category:</label>
            <select class="form-control" id="category" name="category_id">
                <option value="">All Categories</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.category_id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="user">Filter by User:</label>
            <select class="form-control" id="user" name="user_id">
                <option value="">All Users</option>
                <c:forEach items="${users}" var="user">
                    <option value="${user.user_id}">${user.username}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-default">Filter</button>
    </form>

    <table class="table">
        <thead>
            <tr>
                <th>Taken Time</th>
                <th>Category</th>
                <th>Full Name</th>
                <th>No. of Questions</th>
                <th>Score</th>
                <th>Link</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${quizResults}" var="quizResult">
            <tr>
                <td>${quizResult.time_taken}</td>
                <td>${quizResult.category_name}</td>
                <td>${quizResult.fullname}</td>
                <td>${quizResult.count}</td>
                <td>${quizResult.score * 100 / quizResult.count}</td>
                <td><a href="/quiz/result/${quizResult.quiz_id}">View</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- Pagination Links -->
    <ul class="pagination justify-content-center">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <li class="page-item ${i == currentPage ? 'active' : ''}">
                <a class="page-link" href="/admin/quiz-result-management?page=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>

    <form action="/admin" method="GET">
        <button class="btn btn-primary">Back</button>
    </form>
</div>
</body>
<jsp:include page="../footer.jsp" />
</html>