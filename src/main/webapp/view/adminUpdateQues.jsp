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
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(function() {
		easyuuiinit();
		selectallques(1, 8);
	});
	
	function easyuuiinit() {
		$('#win').window({
			title : '题目修改',
			width : 600,
			height : 320,
			closed : true,//初始时是关闭状态
			cache : false,
			modal : true,
			doSize : true,
			border : 'thin',
			yIndex : 300,
		//模态
		});
		$('#dg').datagrid({
			toolbar : '#tb',
			checkOnSelect : false,
			rownumbers : true,
			striped : true,
			fitColumns : true,
			fit : true,
			pagination : true,
			pageNumber : 1,
			pageSize : 8,
			pageList : [ 8, 12, 16 ],
		});
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			onSelectPage : function(pageNumber, pageSize) {
				selectallques(pageNumber, pageSize);
			}
		});
	}

	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function selectallques(pageNumber, pageSize) {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/questionQa/getquestionqaPage.do",
			queryParams : {
				page : pageNumber,
				rows : pageSize
			}
		});
	}

	function updateit(id) {
		$('#Form').form("clear");
		$('#win').window("open");
		$('#Form').form('load',
				'<%=request.getContextPath()%>/questionQa/getquestionqadetils.do?id=' + id);
	}

	function addquestionQa() {
		$('#Form').form("clear");
		$('#win').window("open");
	}

	function closeFun() {
		$('#win').window("close");
	}

	function submitFun() {
		$('#Form').form({
			url : "<%=request.getContextPath()%>/questionQa/updatequesqa.do",
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
					closeFun();
				} else if (data == "insert") {
					$.messager.alert("提示", "添加成功!", "info");
					closeFun();
				}
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
<body>
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">问卷试题管理</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 13%" id="content2">
				<a href="#" class="easyui-linkbutton" onclick="addquestionQa()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 80%" data-options="plain:true,iconCls:'icon-add'">
					<span class="crmbuttonfont">添加试题</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'title',width:180">题目</th>
				<th data-options="field:'optionsA',width:180,align:'center'">选项A</th>
				<th data-options="field:'optionsB',width:180,align:'center'">选项B</th>
				<th data-options="field:'optionsC',width:180,align:'center'">选项C</th>
				<th data-options="field:'optionsD',width:180,align:'center'">选项D</th>
				<th data-options="field:'ww',width:100,formatter:ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<input type="text" name="id" id="id" hidden="true">
			<br>
			<br>
			<div>
				<label style="margin-left: 55px;">标题:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="title" class="easyui-textbox" data-options="required:true,multiline:true" style="width: 410px; height: 60px;">
			</div>
			<br>
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>选项A:</label>
					</td>
					<td>
						<input name="optionsA" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">选项B:</label>
					</td>
					<td>
						<input name="optionsB" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
				</tr>

				<tr>
					<td>
						<label>选项C:</label>
					</td>
					<td>
						<input name="optionsC" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">选项D:</label>
					</td>
					<td>
						<input name="optionsD" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
				</tr>
			</table>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>