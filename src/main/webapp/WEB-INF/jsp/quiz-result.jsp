<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Result</title>
</head>

<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h1>Quiz Result: ${quizName}</h1>
    <h3>User: ${quizUserName}</h3>
    <h3>Score: ${quizScore} ${quizScore >= 60 ? "Pass" : "Fail"}</h3>
    <table class="table">
        <tr>
            <th>Question</th>
            <th>User Selection</th>
            <th>Correct Answer</th>
        </tr>
        <c:forEach items="${quizQuestions}" var="questionResult">
            <tr>
                <td>${questionResult.question.description}</td>
                <td>${questionResult.user_choice_index+1}</td>
                <td>${questionResult.correct_index+1}</td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <ol>
                        <c:forEach items="${questionResult.question.choices}" var="option" varStatus="loop">
                            <li${loop.index==questionResult.user_choice_index && !questionResult.user_correct ? ' class="text-danger"' : ''}
                                ${loop.index==questionResult.correct_index ? ' class="text-success"' : ''}
                            >
                                    ${option.description}
                            </li>
                        </c:forEach>
                    </ol>
                </td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/home" method="GET">
        <input type="hidden" name="retake" value="true">
        <button type="submit" class="btn btn-primary">Retake Quiz</button>
    </form>
</div>
</body>
<jsp:include page="footer.jsp" />
</html>