<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Add Course</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
	<section class="section">
		<div class="container">
			<div class="section_header">
				<h2 class="section_title"><fmt:message key="course.addcourse"/></h2>
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

		<tr>
		<form action="controller" method= "post">
			<td>
				<input type="text" name="course">
			</td>
			<td class="description">
				<textarea name="description" cols="80" rows="3"></textarea>
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="dateFrom">
			</td>
			<td>
				<input min="2020-01-01" max="2023-01-01" type="date" name="dateTo">
			</td>
			<td>
				<select name="teacher">
					<c:forEach items="${teachers}" var="teacher">
						<option value="${teacher.id}">${teacher.firstName} ${teacher.lastName}</option>
					</c:forEach>
				</select>
			</td>
			<td class="button">
					<input type="hidden" name="command" value="addCourse">
					<input type="submit" value="<fmt:message key="button.add"/>"/>
			</td>
		</form>
		</tr>
	</table>
</div>

<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>

</body>


</html>