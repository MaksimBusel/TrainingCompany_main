<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>

<head>
	<meta charset="UTF-8">
	<title>Main</title>
	<link rel="stylesheet" href="css/style.css"/>
</head>
<body>

		<div class="header_inner">
			<a class="header_logo"href="${request.contextPath}controller?command=showMainPage">OnlineTraining</a>
			
			<nav class="nav"> 
				<fmt:message key="message.hello"/><c:out value=", ${user.firstName} ${user.lastName}"/>
				<a class= "nav_link" href="${request.contextPath}controller?command=logout"><fmt:message key="logout.logout"/></a>
				<a class= "nav_link" href="?sessionLocale=en&command=showMainPage"><fmt:message key="language.en"/>  |</a>
				<a class= "nav_link" href="?sessionLocale=ru&command=showMainPage"><fmt:message key="language.ru"/>  |</a>
				<a class= "nav_link" href="?sessionLocale=be&command=showMainPage"><fmt:message key="language.be"/></a>
			</nav>
		</div> 
		<div class="menu">
				<c:if test="${user.role == 'ADMIN'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showEditCoursePage"><fmt:message key="course.editcourse"/></a>
				</c:if>
				<c:if test="${user.role == 'ADMIN'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showAddCoursePage"><fmt:message key="course.addcourse"/></a>
				</c:if>
				<c:if test="${user.role == 'TEACHER'}"> 
					<a class= "nav_link" href="${request.contextPath}controller?command=showTeacherCourses"><fmt:message key="course.mycourses"/></a>
				</c:if>
				<c:if test="${user.role == 'STUDENT'}">
				    <a class= "nav_link" href="${request.contextPath}controller?command=showMyCourses"><fmt:message key="course.mycourses"/></a>
				</c:if>
			</div>
	
</body>

</html>