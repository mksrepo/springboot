<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome123</title>
    </head>
    <body>
        <c:url value="/showMessage.html" var="messageUrl" />
        <a href="${messageUrl}">Show the message</a>
    </body>
</html>