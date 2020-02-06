<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>

<html>
<head>
	<meta charset= "UTF-8"/>
	<title>Footer</title>
	<link href= "css/style.css" rel= "stylesheet"/>
</head>
<body>
		<footer>
			<div class="footer_inner">
				<span><fmt:message key="message.allrights"/>&copy; 2020</span>
				<!--  
				<span class="social"> 
					<a href="#"><img src="/main/webapp/css/img/Facebook.png" alt=""></a>
					<a href="#"><img src="/img/Instagram.png" alt=""></a>
					<a href="#"><img src="/img/VK.png" alt=""></a>
					<a href="#"><img src="/img/Telegram.png" alt=""></a>
				</span>
				!-->
			</div>	
		</footer>
</body>
</html>