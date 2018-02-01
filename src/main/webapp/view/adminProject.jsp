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
	});
	
	function easyuiinit() {
		$('#win').window({
			title : '申请批准',
			width : 600,
			height : 350,
			closed : true,//初始时是关闭状态
			cache : false,
			modal : true,
			doSize : true,
			border : 'thin',
			yIndex : 300,
		//模态
		});
		$(':input[type="checkbox"]').click(function() {
			if ($(this).is(':checked')) {
				$(':input[type="checkbox"]').prop('checked', false);
				$(this).prop('checked', true);
			}
		});
		$('#class').combobox({
			editable : false,
			url : '<%=request.getContextPath()%>/rolePrem/getclass.do',
			valueField : 'id',
			textField : 'className',
			onLoadSuccess : function(data) {
				if (data) {
					$('#class').combobox('setValue', data[0].id);
					selectallstu();
				}
			}
		});
	}

	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function selectallstu() {
		var classId = $("#class").combobox("getValue");
		if (!classId) {
			return;
		}
		$('#dg').datagrid({
			rownumbers:true,
			singleSelect : true,
			toolbar : '#tb',
			striped : true,
			fitColumns : true,
			fit : true,
			url : "<%=request.getContextPath()%>/project/selectallStu.do",
			queryParams : {
				classId : classId,
			},
			onLoadSuccess : function() {
				getclasssta(classId);
			},
		});
	}

	function selectall() {
		$("#class").combobox("clear");
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/project/selectallpro.do",
		})
	}

	function updateit(id, className, userName) {
		$('#Form').form("clear");
		$('#win').window("open");
		$("#id").val(id);
		$("#className").textbox("setValue", className);
		$("#userName").textbox("setValue", userName);
		$('#Form').form('load', '<%=request.getContextPath()%>/project/getproject.do?id=' + id);
	}

	function submitFun() {
		$('#Form').form({
			url : '<%=request.getContextPath()%>/project/updatestu.do',
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			success : function(data) {
				if (data) {
					$.messager.alert("提示", "修改成功!", "info");
					closeFun();
					$('#dg').datagrid('reload');
				}
			}
		});
		// submit the form    
		$('#Form').submit();
	}

	function closeFun() {
		$('#win').window("close");
	}

	var ff = function(value, row, index) {
		var id = row.id;
		var className = row.className;
		var userName = row.userName;
		return "<a onclick='javascript:updateit(" + id + ",\"" + className
				+ "\",\"" + userName + "\"" + ");'>评分</a>";
	}
	function getclasssta(classId) {
		$.ajax({
			url : '<%=request.getContextPath()%>/class/getclasssta.do',
			type : 'POST',
			data : {
				id : classId
			},
			dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				setusersta(data)
			}
		});
	}
	function setusersta(data) {
		$("#searchclassName").text(data.className);
		$("#searchteacherName").text(data.teacherName);
		$("#searchstuNum").text(data.stuNum);
	}
</script>
<body>
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生项目管理</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 15%" id="content1" hidden="true">
				<div style="margin-top: 10px;">
					<table>
						<tr>
							<td>
								<label>班级:</label>
							</td>
							<td>
								<label id="searchclassName" style="border-bottom: 1px solid black; display: block; width: 100px;"></label>
							</td>
							<td>
								<label style="margin-left: 120px;">讲师:</label>
							</td>
							<td>
								<label id="searchteacherName" style="border-bottom: 1px solid black; display: block; width: 100px;"></label>
							</td>
							<td>
								<label style="margin-left: 120px;">班级人数:</label>
							</td>
							<td>
								<label id="searchstuNum" style="border-bottom: 1px solid black; display: block; width: 100px;"></label>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 13%" id="content2">
				&nbsp; &nbsp;&nbsp;当前班级:
				<select id="class" name="class" style="width: 100px; height: 30px;">
				</select>
				<a href="#" class="easyui-linkbutton" onclick="selectallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 200px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="selectall()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 25px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">全部查询</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'className',width:100,align:'center'">班级</th>
				<th data-options="field:'userName',width:120,align:'center'">学生姓名</th>
				<th data-options="field:'projectName',width:220">项目名称</th>
				<th data-options="field:'comScore',width:100,align:'center'">综合评价分</th>
				<th data-options="field:'stuScore',width:100,align:'center'">学生评价分</th>
				<th data-options="field:'teacherScore',width:100,align:'center'">教师评价分</th>
				<th data-options="field:'gradeName',width:100">级别</th>
				<th data-options="field:'size',width:100,align:'center'">空间大小</th>
				<th data-options="field:'remark',width:250">备注</th>
				<th data-options="field:'ids',width:100,formatter: ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<input type="text" name="id" id="id" hidden="true">
			<br>
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>学生班级:</label>
					</td>
					<td>
						<input id="className" class="easyui-textbox" data-options="editable:false," style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">学生姓名</label>
					</td>
					<td>
						<input id="userName" class="easyui-textbox" data-options="editable:false," style="width: 130px; height: 30px;">
					</td>
				</tr>

				<tr>
					<td>
						<label>学生评分</label>
					</td>
					<td>
						<input id="stuScore" name="stuScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">讲师评分</label>
					</td>
					<td>
						<input id="teacherScore" name="teacherScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>综合评分</label>
					</td>
					<td>
						<input id="comScore" name="comScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">评级</label>
					</td>
					<td>
						<select class="easyui-combobox" id="grade" name="grade" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/project/getgradename.do'"
							style="width: 130px; height: 30px;">
						</select>
					</td>
				</tr>
			</table>
			<div>
				<label style="margin-left: 60px;">备注:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="remark" class="easyui-textbox" data-options="multiline:true" style="width: 410px; height: 60px;">
			</div>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 20%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>