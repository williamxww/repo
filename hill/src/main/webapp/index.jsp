<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href=" <%=basePath%>" />
<title>INDEX</title>
<link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.min.css">
<script type="text/javascript">


</script>
</head>

<body>
<h2>welcome to hill</h2>
<button type="button" class="btn btn-default btn-xs">
  <span class="glyphicon glyphicon-user"></span> 中国
</button>
</body>
</html>
