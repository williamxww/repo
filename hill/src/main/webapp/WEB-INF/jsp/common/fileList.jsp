<%@page contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<title>文件展示</title>
<script>
	function download(fileName){
		$("#uploadInput").val(fileName);
		$("#uploadForm").submit();
	}
</script>
</head>

<body>
	<div>
	<table>
	<c:forEach items="${files}" var="f">
		<tr><td><a href="javascript:download('${f.name}')">${f.name}</a></td></tr>
	</c:forEach>
	</table>
	<form id="uploadForm" action="uploadFile.do" style="dispaly:none" method="POST">
		<input id="uploadInput" name="fileName" type="text" />
	</form>	
	</div>
</body>
</html>