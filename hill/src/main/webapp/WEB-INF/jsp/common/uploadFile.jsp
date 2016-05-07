<%@page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<title>上传页面</title>
</head>

<body>
	<div>
		<form action="upload.do" method="POST">
			<input type="file" name="file"/>
			<input type="submit" value="上传文件"  />
		</form>
	</div>
</body>
</html>