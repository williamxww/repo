<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${basePath}/dwr/util.js"></script>  
<script type="text/javascript" src="${basePath}/dwr/engine.js"></script> 
<script type="text/javascript" src="${basePath}/static/js/jquery-1.11.0.min.js"></script> 
<script type="text/javascript">
	$(function(){
		//这句话千万不能少 ，表示允许使用推送技术
		dwr.engine.setActiveReverseAjax(true);
	});
	//这个函数是提供给后台推送的时候  调用的  
	function show(msg){
		$("#message").text(msg);
	}
</script>
<title>Insert title here</title>
</head>  
<body>
	<div id="message" style="width: 200px;height: 200px;border: 1px solid red ; text-align: center; padding: 5px;"></div>
</body>
</html> 