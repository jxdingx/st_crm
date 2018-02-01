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
			title : '菜单修改',
			width : 730,
			height : 500,
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
		$('#roleId').combobox({
			editable : false,
			url : '<%=request.getContextPath()%>/rolePrem/selectRole.do',
			valueField : 'id',
			textField : 'roleName',
			onLoadSuccess : function(data) {
				if (data) {
					$('#roleId').combobox('setValue', data[0].id);
				}
			},
			onChange : function(newValue) {
				selectuser(newValue);
			}
		});
		$('#allmenudl').datalist({
			url : '<%=request.getContextPath()%>/rolePrem/getallmenu.do',
			checkbox : true,
			lines : true,
			striped : true,
			fit : true,
			border : false,
			singleSelect : false,
			valueField : "id",
			textField : "menuName",
			groupField : "menuParentCode",
			groupFormatter : function(value, rows) {
				return "一级菜单:   " + rows[0].menuParentName
			}
		});
		$('#hasmenudl').datalist({
			checkbox : true,
			lines : true,
			striped : true,
			fit : true,
			border : false,
			singleSelect : false,
			valueField : "id",
			textField : "menuName",
			groupField : "menuParentCode",
			groupFormatter : function(value, rows) {
				return "一级菜单:   " + rows[0].menuParentName
			}
		});
	}
	var roleId;
	function selectuser(newValue) {
		roleId = newValue;
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/rolePrem/getRolemenu.do",
			queryParams : {
				roleId : newValue
			}
		});
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function updaterolemenu() {
		$('#win').window("open");
		$('#hasmenudl').datalist({
			url : '<%=request.getContextPath()%>/rolePrem/gethasRolemenu.do',
			queryParams : {
				roleId : roleId
			}
		});
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

	function add() {
		var rows = $('#allmenudl').datalist("getChecked");
		var length = rows.length;
		var checkid = "";
		for (var i = 0; i < length; i++) {
			var row = rows[i];
			var id = row.id;
			checkid += id + ",";
		}
		if (checkid == "") {
			$.messager.alert("提示", "请选择菜单!", "info");
			return;
		}
		$('#hasmenudl').datalist({
			url : '<%=request.getContextPath()%>/rolePrem/updatehasRolemenu.do',
			queryParams : {
				roleId : roleId,
				checkid : checkid
			}
		});
		selectuser(roleId);
	}

	function remove() {
		var rows = $('#hasmenudl').datalist("getChecked");
		var length = rows.length;
		var checkid = "";
		for (var i = 0; i < length; i++) {
			var row = rows[i];
			var id = row.id;
			checkid += id + ",";
		}
		if (checkid == "") {
			$.messager.alert("提示", "请选择菜单!", "info");
			return;
		}

		$('#hasmenudl').datalist({
			url : '<%=request.getContextPath()%>/rolePrem/deletehasRolemenu.do',
			queryParams : {
				roleId : roleId,
				checkid : checkid
			}
		});
		selectuser(roleId);
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
			<div style="margin-top: 10px; font-size: 15px; margin-left: 15%" id="content2">
				&nbsp; &nbsp;&nbsp;当前角色:&nbsp; &nbsp;&nbsp;
				<input id="roleId" style="width: 160px; height: 30px;">
				<a href="#" class="easyui-linkbutton" onclick="updaterolemenu()" style="background: #E0ECFF; width: 150px; height: 31px; margin-left: 7%;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">角色菜单修改</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th
					data-options="field:'type',width:80,formatter:function(value,row){
		        var type=row.menuType;
				if(type==1){
				return '一级菜单';
				}else if(type==2){
				return '二级菜单';
				}
				}">菜单类型</th>
				<th data-options="field:'menuName',width:80">菜单名</th>
				<th data-options="field:'menuUrl',width:80">菜单链接</th>
				<th data-options="field:'descr',width:80">菜单描述</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<table>
				<tr>
					<td>
						<br>
						<div style="margin-left: 40px; float: left; margin-top: 10px;">
							<label style="font-size: 20px; color: rgb(26, 51, 243); margin-top: 20px;">所有菜单:</label>
							<br>
							<br>
							<div style="border: 1px solid #ccc; width: 280px; height: 320px;">
								<ul id="allmenudl">

								</ul>
							</div>
						</div>
					</td>
					<td>
						<a class="easyui-linkbutton" onclick="add()" style="background: rgb(255, 158, 148); width: 60px; height: 34px; margin-left: 8px; margin-top: 70px;" data-options="plain:true">添加->></a>
						<br>
						<a class="easyui-linkbutton" onclick="remove()" style="background: rgb(255, 158, 148); width: 60px; height: 34px; margin-left: 8px; margin-top: 70px;" data-options="plain:true">&lt;&lt;-删除</a>
					</td>
					<td>
						<br>
						<div style="margin-right: 40px; float: right; margin-top: 10px;">
							<label style="font-size: 20px; color: rgb(26, 51, 243); margin-top: 20px;">角色已有菜单:</label>
							<br>
							<br>
							<div style="border: 1px solid #ccc; width: 280px; height: 320px;">
								<ul id="hasmenudl">
								</ul>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<br>
			<br>
			<br>
		</form>
	</div>
</body>
</html>