<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<jsp:include page="../header.jsp" />
<head>


</head>>
<body>
    <div class="container mt-4">
        <h1>Contact us Management Page</h1>
        <hr>
        <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Subject</th>
                <th>Email</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${contact.subject}</td>
                <td>${contact.email}</td>
                <td>${contact.timestamp}</td>
            </tr>
            </tbody>
        </table>
            <p>Message: </p>
        <p>${contact.message}</p>
        <form action="/admin/contact-us-management" method="get">
            <button type="submit">Back</button>
        </form>
    </div>
</body>
<jsp:include page="../footer.jsp" />
</html>