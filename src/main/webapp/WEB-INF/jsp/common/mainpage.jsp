<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Main page</title>
	<link href= "/css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
	<section class="section">
		<div class="container">
			<div class="section_header">
				<h2 class="section_title"><fmt:message key="course.courses"/></h2>
			</div>
		</div>
	</section>

	<table>
		<tr>
			<th><fmt:message key="table.course"/></th>
			<th><fmt:message key="table.description"/></th>
			<th><fmt:message key="table.datefrom"/></th>
			<th><fmt:message key="table.deadline"/></th>
			<th><fmt:message key="table.teacher"/></th>
			<th></th>
		</tr>

		<c:forEach  items="${courses}" var="course">
		<tr>
			<c:if test="${user.role == 'STUDENT'}">		
				<td><c:out value="${course.name}"/></td>
			</c:if >
			<c:if test="${user.role == 'TEACHER'}">
				<td><c:out value="${course.name}"/></td>
			</c:if >
			<c:if test="${user.role == 'ADMIN'}">
				<td><a href="${request.contextPath}controller?command=showCourseStudents&course_id=${course.id}"><c:out value="${course.name}"/></td>
			</c:if >
			<td class="description"><c:out value="${course.description}"/></td>
			<td><c:out value="${course.dateFrom}"/></td>
			<td><c:out value="${course.dateTo}"/></td>
			<td><c:out value="${course.teacherFirstName} ${course.teacherLastName}"/></td>
			<td class="button">
				<c:if test="${user.role == 'STUDENT'}">
				<form action="controller" method= "post">
					<input type="hidden" name="course_id"  value="${course.id}"/>
					<input type="hidden" name="command" value="enrollCourse">
					<input type="submit" value="<fmt:message key="button.enroll"/>"/>
				</form>
				</c:if >
				<c:if test="${user.role == 'ADMIN'}">
				<form action="controller" method= "post">
					<form action="controller" method= "post">
					<input type="hidden" name="course_id"  value="${course.id}"/>
					<input type="hidden" name="command" value="manageTasks">
					<input type="submit" value="<fmt:message key="button.managetasks"/>"/>
				</form>
				</c:if >
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>

</body>


</html>