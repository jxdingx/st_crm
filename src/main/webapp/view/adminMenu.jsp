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
		 easyuiinit() ;
	});
function easyuiinit() {
	$('#dg').datagrid({
		singleSelect : true,
		toolbar : '#tb',
		fit : true,
		fitColumns : true,
		rownumbers : true,
	});
	selectmenu();
	$('#win').window({
		title : '菜单修改',
		width : 520,
		height : 350,
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
	$('#menuParentCode').combobox({
		required : true,
		editable : false,
		url : '<%=request.getContextPath()%>/rolePrem/getmenuOne.do',
		valueField : 'menuCode',
		textField : 'menuName',

	});
}
	function selectmenu() {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/rolePrem/selectmenu.do",
		});
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	// 	function addmenu() {
	// 		$('#win').window("open");
	// 	}
	function updateit(id, menuType) {
		$('#win').window("open");
		$('#Form').form('load', '<%=request.getContextPath()%>/rolePrem/getmenu.do?id=' + id);
		$("#menuParentCode").textbox({
			disabled : menuType == '1' ? true : false
		});
		$("#menuUrl").textbox({
			disabled : menuType == '1' ? true : false
		});
	}

	function submitFun() {
		$('#Form').form({
			url : "<%=request.getContextPath()%>/rolePrem/updatemenu.do",
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			success : function(data) {
				if (data == "update") {
					$.messager.alert("提示", "修改成功!", "info");
				} else if (data == "insert") {
					$.messager.alert("提示", "添加成功!", "info");
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
	function addmenu() {
		$('#win').window("open");
	}
	var ff = function(value, row, index) {
		return "<a onclick='javascript:updateit(" + row.id + ",\""
				+ row.menuType + "\"" + ");'>修改</a>";
	}
</script>
<body>
	<div id="tb">
		<div style="height: 30px; margin-top: 10px;">
			<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">菜单管理</span>
		</div>
		<label onclick="checkhidden('content1')">查看内容 ▼</label>
		<br>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 12%" id="content2">
				<a href="#" class="easyui-linkbutton" onclick="addmenu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 80%;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">添加菜单</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'menuType',width:80,formatter:function(value,row){
				if(value==1){
				return '一级菜单';
				}else if(value==2){
				return '二级菜单';
				}
				}">菜单类型</th>
				<th data-options="field:'menuParentName',width:80,formatter:function(value){
				if(!value){
				return '--无--';
				}
				return value;
				}">上级菜单</th>
				<th data-options="field:'menuName',width:80">菜单名称</th>
				<th data-options="field:'menuUrl',width:80">菜单链接</th>
				<th data-options="field:'descr',width:80">菜单描述</th>
				<th data-options="field:'s',width:80,formatter:ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<br>
			<input name="id" id="id" hidden="true">
			<div>
				<label style="margin-left: 60px;">菜单名称:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="menuName" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 100px; height: 30px;">
				<label style="margin-left: 20px;">上级菜单:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="menuParentCode" id="menuParentCode" style="width: 100px; height: 30px;">
			</div>
			<br>
			<br>
			<div>
				<label style="margin-left: 60px;">菜单链接:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="menuUrl" id="menuUrl" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 200px; height: 30px;">
			</div>
			<br>
			<br>
			<div>
				<label style="margin-left: 58px;">菜单描述:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="descr" class="easyui-textbox" data-options="multiline:true" style="width: 300px; height: 60px;">
			</div>
			<br>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 27%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>