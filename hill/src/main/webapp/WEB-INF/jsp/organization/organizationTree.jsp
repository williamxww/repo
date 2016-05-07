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
	<title>组织结构维护</title>
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="static/css/common.css">
	
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	
		//点击树节点右边的数据重新加载
		function onClickTree(node){
			$("#orgaList").datagrid("load",{id:node.id});
		}

	</script>
</head>
<!-- class in body tag just for full screen -->
<body class="easyui-layout">
	<!-- left accordion area -->
	<div data-options=" region:'west',split:true" style="width:230px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			
			<!-- 组织机构树 -->
			<div title="组织机构树">
				<div class="easyui-panel" data-options="fit:true,border:false" style="padding:5px">
					<ul id="organizationTree" class="easyui-tree" 
						data-options="url:'organization/getOrganizationTree.do',
						method:'get',
						animate:true,
						onClick:onClickTree
						">
					</ul>
				</div>
			</div>
		</div>
	</div>

<!--  数据列表  -->
	<div data-options=" region:'center',title:'节点列表' ">
		<!-- tool bar -->
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="Add" plain="true" onclick="addOrgaNode()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="Delete" plain="true" onclick="deleteOrgaNode()">删除</a>
		</div>

		<!-- 列表 -->
		<table id="orgaList" class="easyui-datagrid"
			data-options="rownumbers:true,
			singleSelect:true,
			selectOnCheck:true,
			checkOnSelect:true,
			fit:true,
			pagination:true,
			fitColumns:true,
			url:'organization/getChildren.do',
			method:'get',
			toolbar:'#toolbar' ">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:80">id</th>
					<th data-options="field:'pid',width:100" >pid</th>
					<th data-options="field:'name',width:80,align:'right' ">name</th>
					<th data-options="field:'code',width:80,align:'right'">code</th>
					<th data-options="field:'type',resize:true">type</th>
					<th data-options="field:'status',width:60,align:'center'">status</th>
					<th data-options="field:'description',width:60,align:'center'">description</th>
				</tr>
			</thead>
		</table>
	</div>	
	
	
</body>
</html>