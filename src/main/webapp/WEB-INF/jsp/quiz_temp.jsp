<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
</head>

<body>
<div>

    <form method="post" action="/quiz">

        <%-- Question description --%>
        <p>${question.description}</p>

        <%-- Dynamically render the choices --%>
        <c:forEach items="${question.choices}" var="choice" varStatus="loop">
            <div>
                <input type="radio"
                       name="selectedChoiceId"
                       value="${choice.choice_id}"/>
                    ${choice.description}
            </div>
        </c:forEach>

        <%-- Button to submit quiz --%>
        <button type="submit">submit</button>

    </form>
</div>
</body>
</html>

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
    </style>
</head>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>Quiz</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-question1-tab" data-toggle="pill" href="#v-pills-question1" role="tab" aria-controls="v-pills-question1" aria-selected="true">Question 1</a>
                <a class="nav-link" id="v-pills-question2-tab" data-toggle="pill" href="#v-pills-question2" role="tab" aria-controls="v-pills-question2" aria-selected="false">Question 2</a>
                <a class="nav-link" id="v-pills-question3-tab" data-toggle="pill" href="#v-pills-question3" role="tab" aria-controls="v-pills-question3" aria-selected="false">Question 3</a>
                <a class="nav-link" id="v-pills-question4-tab" data-toggle="pill" href="#v-pills-question4" role="tab" aria-controls="v-pills-question4" aria-selected="false">Question 4</a>
                <a class="nav-link" id="v-pills-question5-tab" data-toggle="pill" href="#v-pills-question5" role="tab" aria-controls="v-pills-question5" aria-selected="false">Question 5</a>
            </div>
        </div>
        <div class="col-md-9">
            <div class="tab-content" id="v-pills-tabContent">
                <c:forEach items="${questions}" var="question" varStatus="loop">
                    <div class="tab-pane fade" id="v-pills-question${loop.index+1}" role="tabpanel" aria-labelledby="v-pills-question1-tab">
                        <h4>Question ${loop.index+1}}</h4>
                        <p>question.description</p>
                        <c:forEach items="${question.choices}" var="choice" varStatus="loop2">
                            <div>
                                <input type="radio"
                                       name="selectedChoiceId"
                                       value="${choice.choice_id}"/>
                                    ${choice.description}
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
