<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Edit Course</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>
	<c:if test="${result!= null}">
		<div class="message">
			<p><fmt:message key="${result}"/></p>
		</div>
	</c:if>

<div class="content">
	<section class="section">
		<div class="container">
			<div class="section_header">
				<h2 class="section_title"><fmt:message key="course.editcourse"/></h2>
			</div>
		</div>
	</section>

	<table>
		<tr>
			<th><fmt:message key="table.course"/></th>
			<th><fmt:message key="table.description"/></th>
			<th><fmt:message key="table.datefrom"/></th>
			<th><fmt:message key="table.deadline"/></th>
			<th><fmt:message key="table.selectteacher"/></th>
			<th></th>
		</tr>

		<c:forEach items="${courses}" var="course">
		<tr>
		<form action="controller" method= "post">
			<td>
				<input type="text" name="course_name" value="${course.name}">
			</td>
			<td class="description">
				<textarea name="description" cols="80" rows="3">${course.description}</textarea>
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="date_from" value="${course.dateFrom}">
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="date_to" value="${course.dateTo}">
			</td>
			<td>
				<select name="teacher">
					<option value="${course.teacherId}">${course.teacherFirstName} ${course.teacherLastName}</option>
					<c:forEach items="${teachers}" var="teacher">
						<option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
					</c:forEach>
				</select>
			</td>
			<td class="button">
				<input type="hidden" name="course_id" value="${course.id}">
				<input type="hidden" name="command" value="editCourse">
				<input type="submit" value="<fmt:message key="button.edit"/>"/>
		</form>
				<form action="controller" method= "post">
					<input type="hidden" name="course_id" value="${course.id}">
					<input type="hidden" name="command" value="lockCourse">
					<input type="submit" value="<fmt:message key="button.delete"/>"/>
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