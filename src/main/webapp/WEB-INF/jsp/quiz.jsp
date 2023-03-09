<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        /* CSS for quiz pages */
        .quiz-page {
            display: none;
        }
        .quiz-page.active {
            display: block;
        }
        /* CSS for navigator */
        .quiz-nav {
            text-align: center;
            margin-top: 20px;
        }
        .quiz-nav ul {
            list-style: none;
            display: inline-block;
            margin: 0;
            padding: 0;
        }
        .quiz-nav ul li {
            display: inline-block;
            margin: 0 5px;
        }
        .quiz-nav ul li a {
            display: block;
            padding: 10px;
            background-color: #ccc;
            color: #000;
            text-decoration: none;
        }
        .quiz-nav ul li.active a {
            background-color: #333;
            color: #fff;
        }

        .nav-links.active {
            background-color: #007bff;
            color: #fff;
        }
        .nav-link.answered {
            background-color: #22FF00FF;
            color: #fff;
        }
    </style>
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <form action="/quiz/${quiz.quiz_id}" method="post">
        <div class="row">
            <div class="col-md-12">
                <h3>Quiz -- ${quiz.name}</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <c:forEach items="${questions}" var="question" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.index == 0}">
                                <a class="nav-link active" id="v-pills-question1-tab"
                                   data-toggle="pill" href="#v-pills-question1"
                                   role="tab" aria-controls="v-pills-question1"
                                   aria-selected="true">Q1</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" id="v-pills-question${loop.index+1}-tab"
                                   data-toggle="pill" href="#v-pills-question${loop.index+1}"
                                   role="tab" aria-controls="v-pills-question${loop.index+1}"
                                   aria-selected="false">Q${loop.index+1}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="tab-content" id="v-pills-tabContent">
                    <c:forEach items="${questions}" var="question" varStatus="loop">
                        <div class="tab-pane fade <c:if test="${loop.index == 0}">active in</c:if> "
                             id="v-pills-question${loop.index+1}" role="tabpanel" aria-labelledby="v-pills-question${loop.index+1}-tab">
                            <h4>Question ${loop.index+1}</h4>
                            <p>${question.description}</p>
                            <c:forEach items="${question.choices}" var="choice" varStatus="loop2">
                                <div>
                                    <input type="radio" onclick="changeActive(${loop.index+1})"
                                           name="q${loop.index+1}_selectedChoiceId"
                                           value="${choice.choice_id}"
                                    />
                                        ${choice.description}
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>
<script>
    function changeActive(question_index) {
        // Remove the "active" class from all questions in the navigation bar
        //var questions = document.querySelectorAll('.nav-link');
        //questions.forEach(question => question.classList.remove('active'));

        // Add the "active" class to the corresponding question in the navigation bar
        var questionTab = document.getElementById('v-pills-question' + question_index　+ '-tab');
        questionTab.classList.add('answered');
    }
</script>

</body>
</html>
