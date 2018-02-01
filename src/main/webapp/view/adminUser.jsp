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
		rownumbers:true,
	});
	$('#win').window({
		title : '用户修改',
		width : 550,
		height : 410,
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
	$('#excelwin').window({
		title : 'excel添加用户',
		width : 250,
		height : 130,
		closed : true,//初始时是关闭状态
		cache : false,
		modal : true,
		doSize : true,
		border : 'thin',
		yIndex : 300,
	//模态
	});
	$('#excelupwin').window({
		title : 'excel上传',
		width : 350,
		height : 200,
		closed : true,//初始时是关闭状态
		cache : false,
		modal : true,
		doSize : true,
		border : 'thin',
		yIndex : 300,
	//模态
	});
	$('#class').combobox({
		editable : false,
		disabled:true,
		url : '<%=request.getContextPath()%>/rolePrem/getclass.do',
		valueField : 'id',
		textField : 'className',
		loadFilter : function(data) {
			var obj = {};
			obj.id = '';
			obj.className = '-- 全部 --'
			data.splice(0, 0, obj)//在数组0位置插入obj,不删除原来的元素  
			return data;
		},
		onLoadSuccess : function(data) {
			if (data) {
				getsearchcombobox();
				getformcombobox();
			}
		}
	});
}
	function getsearchcombobox() {
		$('#role').combobox({
			editable : false,
			url : '<%=request.getContextPath()%>/rolePrem/selectRole.do',
			valueField : 'id',
			textField : 'roleName',
			loadFilter : function(data) {
				var obj = {};
				obj.id = '';
				obj.roleName = '--全部--'
				data.splice(0, 0, obj)//在数组0位置插入obj,不删除原来的元素  
				return data;
			},
			onLoadSuccess : function(data) {
				if (data) {
					selectuser();
				}
			},
			onChange : function(value) {
				if (value == 3) {
					$('#class').combobox("clear");
					$('#class').combobox("setText", '');
					$('#class').combobox("disable");
				} else {
					$('#class').combobox("clear");
					$('#class').combobox("enable");
				}
			},
		});
	}

	function selectuser() {
		var classId = $("#class").combobox("getValue");
		var roleId = $("#role").combobox("getValue");
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/user/selectUser.do",
			queryParams : {
				roleId : roleId,
				classId : classId
			},
		});
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}
	function getformcombobox() {
		$('#roleId').combobox({
			editable : false,
			url : '<%=request.getContextPath()%>/rolePrem/selectRole.do',
			valueField : 'id',
			textField : 'roleName',
			onChange : function(value) {
				if (value == 3) {
					$('#classId').combobox("clear");
					$('#classId').combobox("setText", '');
					$('#classId').combobox("disable");
				} else {
					$('#classId').combobox({
						required:value==2?false:true,
					});
					$('#classId').combobox("enable");
				}
			},
			onLoadSuccess : function() {
				$('#classId').combobox({
					editable : false,
					url : '<%=request.getContextPath()%>/rolePrem/getclass.do',
					valueField : 'id',
					textField : 'className',
				});
			}
		});
	}

	function adduser() {
		$("#win").window("open");
	}

	function closeFun() {
		$("#win").window("close");
	}
	function updateit(id) {
		$("#win").window("open");
		$('#Form').form('load', '<%=request.getContextPath()%>/user/getuser.do?id=' + id);
	}

	function submitFun() {
		if (!$("#id").val()) {
			if (!$("#password").passwordbox("getValue")) {
				$.messager.alert('提示', '请填写完整!', "error");
				return;
			}
		}
		$('#Form').form({
			url : '<%=request.getContextPath()%>/user/updateuser.do',
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
				} else {
					$.messager.alert("提示", "添加成功!", "info");
				}
				closeFun();
				$('#dg').datagrid('reload');
			}
		});
		// submit the form    
		$('#Form').submit();
	}

	function updatepassword() {
		if (!$("#id").val()) {
			$.messager.prompt('提示信息', '请输入密码:', function(r) {
				if (r) {
					$("#password").passwordbox("setValue", r)
				}
			});
		} else {
			$.messager.prompt('密码重置', '请输入新密码:', function(r) {
				if (r) {
					$("#password").passwordbox("setValue", r)
				}
			});
		}
	}

	var ff = function(value, row, index) {
		return "<a onclick='javascript:updateit(" + row.id + ");'>修改</a>";
	}

	function addmanyuser() {

		$('#excelwin').window("open")
	}

	function exceldown() {
		window.location.href = "<%=request.getContextPath()%>/user/exceldown.do";
		$('#excelwin').window("close")
	}

	function excelup() {
		$('#excelwin').window("close")
		$('#excelupwin').window("open")
	}
	function excelupok() {
		$('#excelForm').form({
			url : '<%=request.getContextPath()%>/user/upexcelfile.do',
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			success : function(data) {
				if (data == "ok") {
					$.messager.alert("提示", "添加成功!", "info");
				} else if (data == "fail") {
					$.messager.alert("提示", "添加失败!", "error");
				}
				$('#excelupwin').window("close");
				$('#dg').datagrid('reload');
			},
		});
		// submit the form    
		$('#excelForm').submit();
	}
	function excelupfail() {
		$('#excelupwin').window("close")
	}
</script>
<body style="width: 100%">
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">用户管理</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 13%" id="content2">
				&nbsp; &nbsp;&nbsp;当前角色:
				<input id="role" style="width: 100px; height: 30px;">
				&nbsp; &nbsp;&nbsp;当前班级:
				<input id="class" style="width: 100px; height: 30px;">
				<a href="#" class="easyui-linkbutton" onclick="selectuser()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 40px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="adduser()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 300px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">添加用户</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="addmanyuser()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">批量添加</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'userName',width:50">账号</th>
				<th data-options="field:'trueName',width:80">姓名</th>
				<th data-options="field:'phone',width:80">电话</th>
				<th data-options="field:'address',width:80">地址</th>
				<th data-options="field:'roleName',width:80">角色</th>
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
						<label>账号:</label>
					</td>
					<td>
						<input id="userName" name="userName" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 20px;">密码:</label>
					</td>
					<td>
						<a onclick="updatepassword()" href="#">
							<input class="easyui-passwordbox" id="password" name="password" data-options="editable:false,showEye:false" style="width: 130px; height: 30px;">
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<label>姓名:</label>
					</td>
					<td>
						<input id="trueName" name="trueName" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 20px;">电话:</label>
					</td>
					<td>
						<input id="phone" name="phone" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
				</tr>

				<tr>
					<td>
						<label>角色:</label>
					</td>
					<td>
						<input id="roleId" name="roleId" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 20px;">班级:</label>
					</td>
					<td>
						<input id="classId" name="classId" class="easyui-textbox" data-options="required:true" style="width: 130px; height: 30px;">
					</td>
				</tr>
			</table>
			<br>
			<div>
				<label style="margin-left: 55px;">地址:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="address" class="easyui-textbox" data-options="required:true,multiline:true" style="width: 365px; height: 60px;">
			</div>
			<br>

			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
	<div id="excelwin">
		<a class="easyui-linkbutton" onclick="exceldown()" style="background: #F4F4F4; width: 100px; height: 80px; margin: 10px 0px 10px 10px;" data-options="plain:true">下载excel模版</a>
		<a class="easyui-linkbutton" onclick="excelup()" style="background: #F4F4F4; width: 100px; height: 80px; margin: 10px 0px 10px 20px;" data-options="plain:true">上传excel</a>
	</div>
	<div id="excelupwin">
		<form method="post" id="excelForm" enctype="multipart/form-data">
			<br>
			<span style="margin-left: 56px;">选择班级:</span>
			&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" id="excelclassId" name="excelclassId"
				data-options="required:true,editable : false,url : '<%=request.getContextPath()%>/rolePrem/getclass.do',valueField : 'id',textField : 'className'" style="width: 100px; height: 30px;">
			<br>
			<br>
			<span style="margin-left: 56px;">选择文件:</span>
			&nbsp;&nbsp;&nbsp;
			<input class="easyui-filebox" name="excelfile" id="excelfile"
				data-options="required:true,prompt:'选择上传.xls文件',buttonText: '选择文件' ,accept: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'"
				style="width: 100% x; height: 30px;">
			<br>
			<br>
			<a class="easyui-linkbutton" onclick="excelupok()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 56px" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="excelupfail()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>