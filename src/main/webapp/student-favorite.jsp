<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Here is a map of student id - favorite teacher id: <br> <br>
<jsp:useBean id="studentIdFavoriteTeacherMap" scope="request" type="java.util.Map"/>
<c:forEach items="${studentIdFavoriteTeacherMap}" var="entry">
    ${entry.key} - ${entry.value}<br>
</c:forEach>

</body>
</html>
