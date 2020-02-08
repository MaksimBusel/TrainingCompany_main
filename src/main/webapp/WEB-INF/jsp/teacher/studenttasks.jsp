<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Student tasks</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
<section class="section">
	<div class="container">
		<div class="section_header">
			<h2 class="section_title">${student_name}'s <fmt:message key="task.tasks"/></h2>
		</div>
	</div>
</section>

<table>
	<tr>
		<th><fmt:message key="table.task"/></th>
		<th><fmt:message key="table.datefrom"/></th>
		<th><fmt:message key="table.deadline"/></th>
		<th><fmt:message key="table.mark"/></th>
		<th><fmt:message key="table.feedback"/></th>
		<th></th>
	</tr>

	<c:forEach  items="${tasks}" var="task">
    <tr>
		<td><c:out value="${task.name}"/></td>
		<td><c:out value="${task.dateFrom}"/></td>
		<td><c:out value="${task.dateTo}"/></td>
		<form action="controller" method= "post">
		<td>
			<select name="mark">
				<option value="${student.mark}">${task.mark}</option>
				<option value="0"></option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>  /10
		</td>
		<td>
			<textarea name="feedback" cols="80" rows="3">${task.feedback}</textarea>
		</td>
		<td>
			<c:if test="${user.role == 'TEACHER'}">	
				<input type="hidden" name="task_id" value="${task.id}">
				<input type="hidden" name="student_task_id" value="${task.studentTaskId}">
				<input type="hidden" name="command" value="estimateTask">
				<input type="submit" value="<fmt:message key="button.estimate"/>"/>
			</form><br/><br/>
			<c:if test="${task.filePath != 'NULL'}">
				<form action="controller" method= "post">
					<input type="hidden" name="student_task_id" value="${task.studentTaskId}">
					<input type="hidden" name="command" value="downloadStudentTask">
					<input type="submit" value="<fmt:message key="button.download"/>"/>
				</form>
			</c:if>
			</c:if>
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