<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../header.jsp" />
<head>


</head>>
<body>
<div class="container mt-4">
    <h1>Question Management Page - Edit</h1>
    <hr>
    <!-- Add Question Button -->
    <form action="/admin/question-management/finish-edit?page=${currentPage}" method="post">
        <div class="form-group">
            <label for="questionDescription">Question Description</label>
            <textarea class="form-control" id="questionDescription" name="description">${question.description}</textarea>
        </div>
        <c:forEach items="${question.choices}" var="choice" varStatus="loop">
            <div class="form-check">
                <label for="choice_${loop.index}">Choice ${loop.index+1}:</label>
                <input type="hidden" name="choices[${loop.index}].choice_id" value="${choice.choice_id}">
                <input type="hidden" name="choices[${loop.index}].question_id" value="${question.question_id}">
                <input id="choice_${loop.index}" name="choices[${loop.index}].description" type="text" value=${choice.description} required>
                <input class="form-check-input" type="checkbox" name="choices[${loop.index}].correct"
                       id="choice_id_${choice.choice_id}" ${choice.correct ? 'checked' : ''}>
            </div>
        </c:forEach>

<%--        <div class="form-group">--%>
<%--            <label for="choice_1">Choice 1:</label>--%>
<%--            <input id="choice_1" name="choices[0].description" type="text" required>--%>
<%--            <label for="choice_1_correct">Correct:</label>--%>
<%--            <input id="choice_1_correct" name="choices[0].correct" type="checkbox">--%>
<%--        </div>--%>
<%--        <div class="form-group">--%>
<%--            <label for="choice_2">Choice 2:</label>--%>
<%--            <input id="choice_2" name="choices[1].description" type="text" required>--%>
<%--            <label for="choice_2_correct">Correct:</label>--%>
<%--            <input id="choice_2_correct" name="choices[1].correct" type="checkbox">--%>
<%--        </div>--%>
<%--        <div class="form-group">--%>
<%--            <label for="choice_3">Choice 3:</label>--%>
<%--            <input id="choice_3" name="choices[2].description" type="text" required>--%>
<%--            <label for="choice_3_correct">Correct:</label>--%>
<%--            <input id="choice_3_correct" name="choices[2].correct" type="checkbox">--%>
<%--        </div>--%>
<%--        <div class="form-group">--%>
<%--            <label for="choice_4">Choice 4:</label>--%>
<%--            <input id="choice_4" name="choices[3].description" type="text" required>--%>
<%--            <label for="choice_4_correct">Correct:</label>--%>
<%--            <input id="choice_4_correct" name="choices[3].correct" type="checkbox">--%>
<%--        </div>--%>
        <input type="hidden" name="question_id" value="${question.question_id}">
        <input type="hidden" name="category_id" value="${question.category_id}">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</body>
<jsp:include page="../footer.jsp" />
</html>