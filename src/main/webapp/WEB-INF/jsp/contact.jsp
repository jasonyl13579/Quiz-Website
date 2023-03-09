<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="header.jsp" />
<body>
<div class="container">
    <div id="contact-form">
        <h3>Contact Us</h3>
        <form action="/contact" method="post">
            <div class="form-group">
                <label for="subject">Subject</label>
                <input type="text" class="form-control" id="subject" name="subject" required>
            </div>
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="time">Time</label>
                <input type="text" class="form-control" id="time" name="timestamp" value="<%= LocalDateTime.now() %>" readonly>
            </div>
            <div class="form-group">
                <label for="message">Message</label>
                <textarea class="form-control" id="message" name="message" rows="5" required></textarea>
            </div>
            <c:if test="${not empty displayMessage}">
                <div class="alert alert-success">
                        ${displayMessage}
                </div>
            </c:if>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <br>

</div>
</body>
<jsp:include page="footer.jsp" />
</html>