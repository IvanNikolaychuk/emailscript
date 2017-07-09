<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="favouriteTeachers" scope="request" type="java.util.List"/>
<jsp:useBean id="topRatedTeachers" scope="request" type="java.util.List"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<body style="margin:0;">
<div style="background:#EDEDED; padding-bottom:20px;text-align:center">
    <h1 style="color:#008D71;margin:0;padding-top: 20px">Teacher Staticstic</h1>
    <c:if test="${!empty favouriteTeachers}">
        <h2 style="color:#031E45">Favourite teachers</h2>
    </c:if>
    <c:forEach items="${favouriteTeachers}" var="favouriteTeacher">
        <h3 style="color:#031E45">Your favourite teacher is: ${favouriteTeacher.name}</h3>
        <div>
            <img src='https://raw.githubusercontent.com/IvanNikolaychuk/emailscript/master/src/main/webapp/photos/${favouriteTeacher.id}.jpg'
                 style=" height:400px;width:auto;border-radius:5%">
            <br><br>
            <i>
                Study Major: ${favouriteTeacher.studyMajor} <br><br>

                University: ${favouriteTeacher.university}<br>
                Occupation: ${favouriteTeacher.occupation}<br><br>
                Jap Level: ${favouriteTeacher.japLevel}<br>
                Jap Jess Level: ${favouriteTeacher.japJessLevel}<br>
            </i>
            <br>
            <i>Avarage score is: ${favouriteTeacher.avgScore}</i>
            <div>
                <br><br>
                <b>Curriculum</b><br><br>
                <c:forEach items="${favouriteTeacher.curriculum}" var="time">
                    <div>${time}</div>
                </c:forEach>
            </div>

        </div>
        <br><br><br>
    </c:forEach>
    <c:if test="${!empty topRatedTeachers}">
        <h2 style="color:#031E45">Teacher with 'A' rating</h2>
    </c:if>
    <c:forEach items="${topRatedTeachers}" var="topRatedTeacher">
        <div style="border-top:1px solid #a1aab8; margin-top: 4px">
            <h3 style="color:#031E45">${topRatedTeacher.name}</h3>
            <img src='https://raw.githubusercontent.com/IvanNikolaychuk/emailscript/master/src/main/webapp/photos/${topRatedTeacher.id}.jpg'
                 style="padding-top:50px;height:400px;width:auto;border-radius:5%">
            <br><br>
            <i>
                Study Major: ${topRatedTeacher.studyMajor} <br><br>

                University: ${topRatedTeacher.university}<br>
                Occupation: ${topRatedTeacher.occupation}<br><br>
                Jap Level: ${topRatedTeacher.japLevel}<br>
                Jap Jess Level: ${topRatedTeacher.japJessLevel}<br>
            </i>
            <br>
            <i>Avarage score is: ${topRatedTeacher.avgScore}</i>
            <div>
                <br><br>
                <b>Curriculum</b><br><br>
                <c:forEach items="${topRatedTeacher.curriculum}" var="time">
                    <div>${time}</div>
                </c:forEach>
            </div>

        </div>
    </c:forEach>


</div>
</body>
</html>