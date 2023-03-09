<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="header.jsp" />
<head>

    <style>
        .category-btns {
            display: flex;
            flex-direction: row;
        }
        .category-btns form {
            margin-right: 1rem;
        }
    </style>

</head>>
<body>
<div class="container">
    <h2>Category</h2>

    <div class="category-btns">
        <c:forEach items="${categories}" var="category" varStatus="loop">
            <form action="/quiz/new-quiz/${category.category_id}" method="get">
                <button class="btn btn-default">${category.name}</button>
            </form>
        </c:forEach>
    </div>
    <h2>Recent quiz result</h2>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Date Taken</th>
            <th>Result</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="quiz" items="${quizList}">
            <tr>
                <td>${quiz.name}</td>
                <td>${quiz.time_start}</td>
                <td><a href="${pageContext.request.contextPath}/quiz/result/${quiz.quiz_id}">View Result</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<jsp:include page="footer.jsp" />
</html>