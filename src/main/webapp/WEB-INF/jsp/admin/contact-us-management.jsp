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
                <th>Message</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contacts}" var="contact">
                <tr>
                    <td>${contact.subject}</td>
                    <td>${contact.email}</td>
                    <td>${contact.timestamp}</td>
                    <td>
                        <c:if test="${fn:length(contact.message) > 50}">
                            ${fn:substring(contact.message, 0, 50)}...
                        </c:if>
                        <c:if test="${fn:length(contact.message) <= 50}">
                            ${contact.message}
                        </c:if>
                    </td>
                    <td><a href="/admin/contact/view/${contact.contact_id}">View</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
<jsp:include page="../footer.jsp" />
</html>