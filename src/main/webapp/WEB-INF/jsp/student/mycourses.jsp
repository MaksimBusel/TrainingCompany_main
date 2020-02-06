<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>MyCourses page</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
<section class="section">
	<div class="container">
		<div class="section_header">
			<h2 class="section_title"><fmt:message key="course.mycourses"/></h2>
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

	<c:forEach  items="${myCourses}" var="course">
    <tr>
		<td><a href="${request.contextPath}controller?command=showCourseTasks&course_id=${course.id}">${course.name}</a></td>
		<td class="description"><c:out value="${course.description}"/></td>
		<td><c:out value="${course.dateFrom}"/></td>
		<td><c:out value="${course.dateTo}"/></td>
		<td><c:out value="${course.teacherFirstName} ${course.teacherLastName}"/></td>
		<td class="button">
			<form action="controller" method= "post">
			    <input type="hidden" name="course_id"  value="${course.id}"/>
            	<input type="hidden" name="command" value="unenrollCourse">
				<input type="submit" value="<fmt:message key="button.unenroll"/>"/>
			</form>
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