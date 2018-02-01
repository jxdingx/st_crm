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
		$.ajax({
			url : '<%=request.getContextPath()%>/user/getusersta.do',
			type : 'POST',
			data : {
				id : '${user.id}'
			},
			dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				setusersta(data)
			}
		});
		selectstu();
		$('#win').window({
			title : '请假申请表',
			width : 650,
			height : 350,
			closed : true,//初始时是关闭状态
			cache : false,
			modal : true,
			doSize : true,
			border : 'thin',
			yIndex : 300,
		//模态
		});
	}

	function addproject() {
		$('#win').window("open");
	}

	function selectstu() {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/project/selectStu.do",
		});
	}
	function submitFun() {
		$('#Form').form({
			url : '<%=request.getContextPath()%>/project/updatestu.do',
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '申请表未填写完整!', "error");
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
		$('#Form').submit();
	}
	function closeFun() {
		$('#win').window("close");
		$('#dg').datagrid("reload");
	}
	var ff = function(value, row, index) {
		return "<a onclick='javascript:updateit(" + row.id + ");'>修改</a>";
	}

	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}
	function setusersta(data) {
		$("#userclassName").text(data.classModel.className);
		$("#usercourseName").text(data.classModel.courseName);
		$("#userteacherName").text(data.classModel.teacherName);
		$("#usertrueName").text(data.trueName);
		$("#userid").text(data.id);
		$("#userphone").text(data.phone);
		$("#searchuserclassName")
				.textbox("setValue", data.classModel.className);
		$("#searchusertrueName").textbox("setValue", data.trueName);

	}
</script>
<body>
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生反馈表</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 12%" id="content1" hidden="true">
				<div style="margin-top: 10px;">
					<table>
						<tr>
							<td>
								<label>班级:</label>
							</td>
							<td style="width: 100px;">
								<label id="userclassName" style="border-bottom: 1px solid black; display: block; margin-top: 22px;"></label>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td>
								<label style="margin-left: 120px;"> 课程:</label>
							</td>
							<td style="width: 120px;">
								<label id="usercourseName" style="border-bottom: 1px solid black; display: block;"></label>
							</td>
							<td>
								<label style="margin-left: 120px;"> 讲师:</label>
							</td>
							<td style="width: 120px;">
								<label id="userteacherName" style="border-bottom: 1px solid black; display: block;"></label>
						</tr>
						<tr>
							<td>
								<label> 学生编号:</label>
							</td>
							<td>
								<label id="userid" style="border-bottom: 1px solid black; display: block;"></label>
							</td>
							<td>
								<label style="margin-left: 120px;">学生姓名:</label>
							</td>
							<td>
								<label id="usertrueName" style="border-bottom: 1px solid black; display: block;"></label>
							</td>
							<td>
								<label style="margin-left: 120px;">电话:</label>
							</td>
							<td>
								<label id="userphone" style="border-bottom: 1px solid black; display: block;"></label>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 12%" id="content2">
				班级:
				<input id="searchuserclassName" class="easyui-textbox" style="margin-left: 20px;" data-options="editable:false">
				&nbsp; &nbsp;&nbsp;姓名:
				<input id="searchusertrueName" class="easyui-textbox" style="margin-left: 20px;" data-options="editable:false">
				<a href="#" class="easyui-linkbutton" onclick="addproject()" style="background: #E0ECFF; width: 100px; margin-right: 80px; height: 34px; float: right;" data-options="plain:true,iconCls:'icon-add'">
					<span class="crmbuttonfont">新增项目</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'projectName',width:50">项目名称</th>
				<th data-options="field:'comScore',width:80">综合评价分</th>
				<th data-options="field:'stuScore',width:80">学生评价分</th>
				<th data-options="field:'teacherScore',width:80">教师评价分</th>
				<th data-options="field:'grade',width:80">级别</th>
				<th data-options="field:'size',width:80">空间大小</th>
				<th data-options="field:'remark',width:150">备注</th>
				<th data-options="field:'s',width:80,formatter: ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<span>项目名称</span>
					</td>
					<td>
						<input name="projectName" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 380px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<span>大小</span>
					</td>
					<td>
						<input name="size" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 380px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<span>备注:</span>
					</td>
					<td>
						<input name="remark" class="easyui-textbox" data-options="multiline:true" style="width: 380px; height: 60px;">
					</td>
				</tr>
			</table>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>