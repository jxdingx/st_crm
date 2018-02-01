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
			rownumbers:true,
			singleSelect : true,
			toolbar : '#tb',
			fit : true,
			fitColumns : true,
		});
		$('#win').window({
			title : '用户修改',
			width : 570,
			height : 360,
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
		selectclass();
		$('#teacherId').combobox({
			required : true,
			editable : false,
			url : '<%=request.getContextPath()%>/class/getteacher.do',
			valueField : 'id',
			textField : 'trueName',
		});
	}

	function selectclass() {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/class/selectClass.do",
		});
	}

	function addclass() {
		$("#win").window("open");
	}

	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function closeFun() {
		$("#win").window("close");
	}

	function updateit(id) {
		$("#win").window("open");
		$('#Form').form('load', '<%=request.getContextPath()%>/class/getclasssta.do?id=' + id);
	}

	function submitFun() {
		$('#Form').form({
			url : '<%=request.getContextPath()%>/class/updateclass.do',
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

	var ff = function(value, row, index) {
		return "<a onclick='javascript:updateit(" + row.id + ");'>修改</a>";
	}
</script>
<body style="width: 100%">
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">班级管理</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 13%" id="content2">
				<a href="#" class="easyui-linkbutton" onclick="addclass()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 80%;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">添加班级</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'className',width:50">班级名称</th>
				<th data-options="field:'courseName',width:80">课程名</th>
				<th data-options="field:'teacherName',width:80">讲师</th>
				<th data-options="field:'stuNum',width:80">班级人数</th>
				<th data-options="field:'descr',width:80">描述</th>
				<th data-options="field:'s',width:80,formatter:ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<br>
			<input id="id" name="id" hidden="true">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="10">
				<tr>
					<td>
						<label>班级名称:</label>
					</td>
					<td>
						<input name="className" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 6px;">课程名:</label>
					</td>
					<td>
						<input name="courseName" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>讲师:</label>
					</td>
					<td>
						<input id="teacherId" name="teacherId" style="width: 130px; height: 30px;">
					</td>
				</tr>
			</table>
			<br>
			<div>
				<label style="margin-left: 55px;">描述:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="descr" class="easyui-textbox" data-options="multiline:true" style="width: 365px; height: 60px;">
			</div>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>