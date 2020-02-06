<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
    <head>
		<meta charset= "UTF-8"/>
        <title><fmt:message key="message.loginpage"/></title>
		<link href= "/css/style.css" rel= "stylesheet"/>
    </head>
    <body>
		<c:if test="${error != null}">			
			<div class="error">
				<p><fmt:message key="message.roleerror"/></p>
			</div>
		</c:if >
		<div class="login_container">
			<div class="login">
				<form action="controller" method="POST">
					<p><fmt:message key="message.login"/>:</p>
					<input type="text" name="login">
					<p><fmt:message key="message.password"/></p>
					<input type="password" name="password">
					<input type="hidden" name="command" value="login">
					<br/><br/>
					<input type="submit" value="<fmt:message key="button.login"/>">
				</form>
				<div class="lang">
					<a class= "language" href="?sessionLocale=en&command=showMainPage"><fmt:message key="language.en"/> |</a>
					<a class= "language" href="?sessionLocale=ru&command=showMainPage"><fmt:message key="language.ru"/> |</a>
					<a class= "language" href="?sessionLocale=be&command=showMainPage"><fmt:message key="language.be"/></a>
				</div>
			</div>
		</div>
    </body>
</html>
