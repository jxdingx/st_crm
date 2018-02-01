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
		 easyuiinit();
		selectallques(1, 8);
	});
	
	function easyuiinit() {
		$('#win').window({
			title : '数据修改',
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
			pageSize : 15,
			pageList : [ 15, 20, 30 ],
		});
		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			onSelectPage : function(pageNumber, pageSize) {
				selectallques(pageNumber, pageSize);
			}
		});
		$('#pcode').combobox({
			required : true,
			editable : false,
			url : '<%=request.getContextPath()%>/dictionary/getpcode.do',
			valueField : 'code',
			textField : 'name',
		});
	}

	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function selectallques(pageNumber, pageSize) {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/dictionary/getdictionaryPage.do",
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
				'<%=request.getContextPath()%>/dictionary/getdictionarydetils.do?id=' + id);
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
			url : "<%=request.getContextPath()%>/dictionary/updatedictionary.do",
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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">基础数据</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 13%" id="content2">
				<a href="#" class="easyui-linkbutton" onclick="addquestionQa()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 80%" data-options="plain:true,iconCls:'icon-add'">
					<span class="crmbuttonfont">添加数据</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'name',width:180">名称</th>
				<th data-options="field:'pcodeName',width:180,align:'center',formatter:function(value){
				if(!value){
				return '--无--';
				}
				return value;
				}">上级</th>
				<th data-options="field:'type',width:180,align:'center'">类型</th>
				<th data-options="field:'descr',width:180,align:'center'">描述</th>
				<th data-options="field:'ww',width:100,formatter:ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<input type="text" name="id" id="id" hidden="true">
			<br>
			<br>
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>名称:</label>
					</td>
					<td>
						<input name="name" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 40px;">类型:</label>
					</td>
					<td>
						<input name="type" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
				</tr>

				<tr>
					<td>
						<label>所属上级:</label>
					</td>
					<td>
						<input name="pcode" id="pcode" class="easyui-combobox" data-options="required:true" style="width: 130px; height: 30px;">
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