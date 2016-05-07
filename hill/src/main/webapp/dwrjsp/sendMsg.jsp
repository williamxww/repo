<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath}/dwr/util.js"></script>    
<script type="text/javascript" src="${basePath}/dwr/engine.js"></script>  
<script type="text/javascript" src="${basePath}/dwr/interface/SendMsg.js"></script>   
<script type="text/javascript" src="${basePath}/static/js/jquery-1.11.0.min.js"></script>
   
<script type="text/javascript">
    $(function(){  
    	
        dwr.engine.setActiveReverseAjax(true);  
        $("#but").click(function(){  
            SendMsg.sendMsg($("#msg").val());  
        });  
    });  
</script>
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="msg"  />  
    <input type="button" value="发送" id="but" /> 
</body>
</html>