<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/crm.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/icon.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui-lang-zh_CN.js"></script>
<title></title>
</head>
<script type="text/javascript">
	$(function() {
		easyuiinit();
	});
function easyuiinit() {
	$('#dg').datagrid({
		singleSelect : true,
		toolbar : '#tb',
		fit : true,
		fitColumns : true,
	});
	selectuser();
	$('#win').window({
		title : '角色修改',
		width : 480,
		height : 300,
		closed : true,//初始时是关闭状态
		cache : false,
		modal : true,
		doSize : true,
		border : 'thin',
		yIndex : 300,
		onClose : function() {
			$('#Form').form("clear");
		}
	//模态
	});
}
	function selectuser() {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/rolePrem/selectRole.do",
		});
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function addrole() {
		$('#win').window("open");
		$('#Form').form("clear");
	}
	function updateit(id) {
		$('#win').window("open");
		$('#Form').form('load', '<%=request.getContextPath()%>/rolePrem/getrole.do?id=' + id);
	}
	function submitFun() {
		$('#Form').form({
			url : "<%=request.getContextPath()%>/rolePrem/updaterole.do",
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			success : function(data) {
				if (data == "insert") {
					$.messager.alert("提示", "添加成功!", "info");
				} else if (data == "update") {
					$.messager.alert("提示", "修改成功!", "info");
				}
				closeFun();
				$('#dg').datagrid('reload');
			}
		});
		// submit the form    
		$('#Form').submit();
	}
	function closeFun() {
		$('#win').window("close");
	}
	var ff = function(value, row, index) {
		return "<a onclick='javascript:updateit(" + row.id + ");'>修改</a>";
	}
</script>
<body>
	<div id="tb">
		<div style="height: 30px; margin-top: 10px;">
			<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">角色管理</span>
		</div>
		<label onclick="checkhidden('content1')">查看内容 ▼</label>
		<br>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 12%" id="content2">
				<a href="#" class="easyui-linkbutton" onclick="addrole()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 80%;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">添加角色</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id',width:50">id</th>
				<th data-options="field:'roleName',width:80">角色名</th>
				<th data-options="field:'descr',width:80">角色描述</th>
				<th data-options="field:'s',width:80,formatter:ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<br>
			<input name="id" id="id" hidden="true">
			<div>
				<label style="margin-left: 60px;">角色名:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="roleName" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 100px; height: 30px;">
			</div>
			<br>
			<br>
			<div>
				<label style="margin-left: 45px;">角色描述:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="descr" class="easyui-textbox" data-options="required:true,multiline:true" style="width: 300px; height: 60px;">
			</div>
			<br>
			<br>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 27%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>