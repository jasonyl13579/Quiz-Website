<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../header.jsp" />
<head>


</head>>
<body>
    <div class="container mt-4">
        <h1>Question Management Page</h1>
        <hr>

        <!-- Add Question Button -->
        <a href="/admin/question-management/question/add" class="btn btn-success mb-2">Add Question</a>

        <!-- Questions Table -->
        <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Category</th>
                <th>Description</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <td>${categoryMap.get(question.category_id)}</td>
                    <td>${question.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${question.active}">
                                <span class="badge badge-success">Active</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger">Suspended</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <!-- Edit Question Button -->
                        <a href="/admin/question-management/${question.question_id}/edit?page=${currentPage}" class="btn btn-primary btn-sm mr-2">Edit</a>

                        <!-- Toggle Question Status Button -->
                        <form action="/admin/question-management/${question.question_id}/toggle-status?page=${currentPage}" method="post" style="display: inline-block;">
                            <button type="submit" class="btn btn-secondary btn-sm">${question.active ? 'Suspend' : 'Activate'}</button>
                            <input type="hidden" name="_method" value="put" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Pagination Links -->
        <ul class="pagination justify-content-center">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="/admin/question-management?page=${i}">${i}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
<jsp:include page="../footer.jsp" />
</html>