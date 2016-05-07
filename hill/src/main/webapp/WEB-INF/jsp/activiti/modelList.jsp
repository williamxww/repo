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
	<script type="text/javascript">
		// 满足某种条件的值突出显示 styler:cellStyler
		function cellStyler(value,row,index){
			if (value < 30){
				return 'background-color:#ffee00;color:red;';
			}
		}
		
		//双击和点击链接都可以打开编辑页 相关方法注意从源码中找
		function onDblClickRow(rowIndex, rowData){
			editData(rowIndex);
		}
		function createLink(value,row,index){
			return "<a href='javascript:void(0);' onclick='showFlowChart("+value+")' >"+value+"</a>";
		}
		function editData(index){
			$('#w').window('open')
			$('#ff').form('load',$('#dg').datagrid('getRows')[index]);
		}

		function addModel(){
			$('#ff').form('clear');//先清空再打开
			$('#w').window('open');
		}

		
		
		function deleteModel(){
			var rows = $('#dg').datagrid('getSelections');
			var ids = [];
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				ids.push(row.id);
			}
			var message = '确认删除  '+ids.join(",");
			$.messager.confirm('删除确认', message , function(r){
				if ( r && (!!rows[0]) ){
					$.ajax({
						url:"model/"+rows[0].id+"/delete.do",
						type:"POST",
						success:function(){
							$('#dg').datagrid("reload");
						}
					});
				}
			});
		}

		function treeHandler(node){
//			var node = $('#tt').tree('getSelected');
			if (node){
				var s = node.text;
				if (node.attributes){
					s += ","+node.attributes.p1+","+node.attributes.p2;
				}
				$.messager.alert('Tree',s,'info');
			}
		}

		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
		
		function createModel(){
			$("#modelMetaData").submit();
		}
		
		//流程图展示
		function showFlowChart(modelId){
			$('#flowChart').dialog('open');
			var html = "<img alt='flowchart' src='model/showModelImage.do?modelId="+modelId+"' />";
			$('#flowChart').empty();
			$('#flowChart').append(html);
		}
		
		function deploy(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length<=0){
				$.messager.alert("提示","请选择需要部署的模型<br/><br/>");
			}else{
				var postData = {modelId:rows[0].id};
				$.ajax({
					url:"model/deploy.do",
					method:"post",
					data:postData,
					success:function(response){
						$.messager.alert("提示",response.msg);
					}
				});
			}
		}
		

	</script>
</head>
<!-- class in body tag just for full screen -->
<body class="easyui-layout">
	<!-- left accordion area -->
	<div data-options=" region:'west',split:true" style="width:200px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			
			<!-- 查询区域 -->
			<div title="Conditions" data-options="selected:true" style="padding:10px;">
				<p>date input:</p>
				<input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:100%;"/>
				<p>Amount:</p>
				<input class="easyui-numberbox" data-options="min:10,max:90,precision:2,required:false" style="width:100%;" />
			</div>
		</div>
	</div>

<!--  数据列表  -->
	<div data-options=" region:'center',title:'模型列表' ">
		<!-- tool bar -->
		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addModel()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteModel()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="deploy()">发布</a>
		</div>

		<!-- 列表 -->
		<table id="dg" class="easyui-datagrid"
			data-options="rownumbers:true,
			singleSelect:true,
			selectOnCheck:true,
			checkOnSelect:true,
			onDblClickRow:onDblClickRow,
			fit:true,
			pagination:true,
			fitColumns:true,
			url:'model/modelList.do',
			method:'get',
			toolbar:'#toolbar' ">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id',width:80,formatter:createLink">id</th>
					<th data-options="field:'name',width:100" >name</th>
					<th data-options="field:'key',width:80,align:'right' ">key</th>
					<th data-options="field:'category',width:80,align:'right'">category</th>
					<th data-options="field:'createTime',resize:true,width:''">createTime</th>
					<th data-options="field:'lastUpdateTime',width:60,align:'center'">lastUpdateTime</th>
					<th data-options="field:'version',width:60,align:'center'">version</th>
					<th data-options="field:'deploymentId',width:60,align:'center'">deploymentId</th>
					<th data-options="field:'editorSourceValueId',width:60,align:'center'">sourceId</th>
					<th data-options="field:'editorSourceExtraValueId',width:60,align:'center'">extraId</th>
				</tr>
			</thead>
		</table>
	</div>	
	
	<!-- 流程图展示区 -->
	<div id="flowChart" class="easyui-dialog" 
		title="流程图" data-options="iconCls:'icon-save',closed:true" 
		style="width:830px;height:360px;padding:10px">
	</div>


<!-- 弹出框 -->

	<!-- 窗口上的按钮 -->
	<div id="saveButton">
		<a href="javascript:void(0)" class="icon-save" iconCls="icon-save" onclick="createModel()"></a>
	</div>

	<!-- 编辑页面 -->
	<div id="w" class="easyui-window" title="新增模型" 
		data-options="title:'新增模型', 
					closed:true,
					tools:'#saveButton',
					collapsible:false,
					minimizable:false,
					maximizable:false" 
					style="width:300px;height:180px;">
		
		<div class="easyui-panel" data-options="fit:true" style="width:400px;border:0">
			<div style="padding:10px 10px;">
			<form id="modelMetaData" method="post" action="model/create.do" data-options="toolbar:'#toolbar'">
				<table cellpadding="5">
					<tr>
						<td>name:</td>
						<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>description:</td>
						<td><input class="easyui-textbox" name="description" style="height:60px" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</div>

	
	
</body>
</html>