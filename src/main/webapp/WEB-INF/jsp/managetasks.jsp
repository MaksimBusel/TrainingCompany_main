<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
<head>
	<meta  http-equiv="Content-Type" content="text/html; charset="UTF-8"/>
	<title>Manage task</title>
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
					<h2 class="section_title"><fmt:message key="button.managetasks"/></h2>
				</div>
			</div>
		</section>

		<table>
				<tr>
					<th><fmt:message key="table.task"/></th>
					<th><fmt:message key="table.datefrom"/></th>
					<th><fmt:message key="table.deadline"/></th>
					<th class="column_button"></th>
				</tr>
				<c:forEach  items="${tasks}" var="task">
					<tr>
					<form action="controller" method= "post">
						<td><input type="text" name="name" value="${task.name}"></td>
						<td><input min="2020-01-01" max="2023-01-01" type="date" name="date_from" value="${task.dateFrom}"></td>
						<td><input min="2020-01-01" max="2023-01-01" type="date" name="date_to" value="${task.dateTo}"></td>
						<td>
							<form action="controller" method= "post">
								<input type="hidden" name="course_id" value="${courseId}">
								<input type="hidden" name="task_id" value="${task.id}">
								<input type="hidden" name="command" value="editTask">
								<input type="submit" value="<fmt:message key="button.edit"/>"/>
							</form><br/><br/>
							<form action="controller" method= "post">
								<input type="hidden" name="task_id" value="${task.id}">
								<input type="hidden" name="course_id" value="${courseId}">
								<input type="hidden" name="command" value="lockTask">
								<input type="submit" value="<fmt:message key="button.delete"/>"/>
							</form>
						</td>
					</form>
					</tr>
				</c:forEach>
		</table>

		<div class="button_marks">
			<form action="controller" method= "post">
				<input type="hidden" name="course_id" value="${courseId}">
				<input type="hidden" name="command" value="showAddTaskPage">
				<input type="submit" value="<fmt:message key="button.addtask"/>"/>
			</form>
		</div>
	</div>

	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
</body>

</html>