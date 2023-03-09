<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../header.jsp" />
<head>


</head>>
<body>
<div class="container mt-4">
    <h1>Question Management Page - Add</h1>
    <hr>
    <!-- Add Question Button -->
    <form action="/admin/question-management/finish-add" method="post">
        <div class="form-group">
            <label for="category_id">Category:</label>
            <select class="form-control" id="category_id" name="category_id">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.category_id}" >${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="questionDescription">Question Description</label>
            <textarea class="form-control" id="questionDescription" name="description" required></textarea>
        </div>
        <c:forEach var="i" begin="0" end="3" varStatus="loop">
            <div class="form-check">
                <label for="choice_${loop.index}">Choice ${loop.index+1}:</label>
                <input id="choice_${loop.index}" name="choices[${loop.index}].description" type="text" required>
                <input class="form-check-input" type="radio" name="choices[${loop.index}].correct" >

            </div>
        </c:forEach>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</body>
<jsp:include page="../footer.jsp" />
</html>