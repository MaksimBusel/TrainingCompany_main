<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Course students</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>

<body>

<div class="content">
<section class="section">
	<div class="container">
		<div class="section_header">
			<h2 class="section_title"><fmt:message key="student.students"/></h2>
		</div>
	</div>
</section>

<table>
	<tr>
		<th><fmt:message key="student.student"/></th>
		<th></th>
	</tr>

	<c:forEach  items="${students}" var="student">
    <tr>
		<td><a href="${request.contextPath}controller?command=showStudentTasks&student_id=${student.id}&course_id=${course_id}&name=${student.lastName}">${student.firstName} ${student.lastName}</a></td>
		<td></td>
	</tr>
    </c:forEach>
</table>

</div>
<div class="footer">
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
</div>

</body>
</html>