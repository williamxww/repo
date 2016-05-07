<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<base href=" <%=basePath%>" />
	<title>流程模型展示页</title>
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="static/css/common.css">
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
	
	
</head>

<body>
	<div class="easyui-panel" data-options="" style="border:1; padding:0;">
		<form id="ff" method="post" data-options="">
			<table cellpadding="5" border="0" width="100%">
				<tr>
					<td width="10%" class="right"><label >标签：</label></td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="itemid" data-options="required:true" ></input></td>
					<td width="10%" class="right">Product:</td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="productid" /></td>
				</tr>
				<tr>
					<td class="right">Product:</td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="productid" /></td>
					<td class="right">Product:</td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="productid" /></td>
				</tr>
				<tr>
					<td class="right">List Price:</td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="listprice" /></td>
					<td class="right">Product:</td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="productid" /></td>
				</tr>
				<tr>
					<td class="right">Status:</td>
					<td>
						<select class="easyui-combobox" name="status" data-options="panelHeight:'auto'">
							<option value="P">P</option>
							<option value="NP">NP</option>
						</select>
					</td>
					<td class="right">Product:</td>
					<td><input class="easyui-textbox" style="width:300px;" type="text" name="productid" /></td>
				</tr>
				<tr>
					<td class="right">Attribute:</td>
					<td colspan="3"><input class="easyui-textbox" style="width:70%;height:60px" name="attr1" data-options="multiline:true"/></td>
				</tr>
			</table>
		</form>
		<div class="easyui-tabs" data-options="tabWidth:112" style="width:100%;height:250px">
			<div title="页签1">
				<!-- tool bar -->
				<div id="toolbar" style="border:solid red 1px;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addData()">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteData()">删除</a>
				</div>
				<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
				
			</div>
			<div title="页签2" style="padding:10px">
				Content
			</div>
			<div title="页签3" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
				This is the help content.
			</div>
		</div>
	</div>

</body>
</html>