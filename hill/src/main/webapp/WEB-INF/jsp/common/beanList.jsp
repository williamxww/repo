<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=" <%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>spring context bean list</title>
<link rel="stylesheet" type="text/css" href="static/css/common.css">
</head>
<body>
<table class="td-border">
	<c:forEach items="${beanDefinitions}" var="bd">
		<tr>
			<td>${bd.key}</td>
			<td>${bd.value}</td>
			<td>${bd.value.singleton}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>