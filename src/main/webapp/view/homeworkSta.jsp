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
			striped : true,
			fitColumns : true,
			fit : true,
			url : "<%=request.getContextPath()%>/homesta/selecthomework.do",
		});
		$('#win').window({
			title : '个人作业自评',
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
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}
	function updateit(id, homeworkId, status) {
		if (status == "1") {
			$.messager.alert("提示", "已锁定,不可修改!", "warning");
			return;
		}
		$('#Form').form("clear");
		$('#win').window("open");
		$("#id").val(id);
		$("#homeworkId").val(homeworkId);
		$("#userName").textbox("setValue", '${user.trueName}');
		$("#score").numberspinner({
			min : 0,
			max : 100,
		});
		if (id != 0) {
			$('#Form').form('load', '<%=request.getContextPath()%>/homesta/gethomestadetils.do?id=' + id);
		}
	}
	function submitFun() {
		$('#Form').form({
			url : "<%=request.getContextPath()%>/homesta/updatehomeworkstu.do",
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			queryParams : {
				checkuserId : '${user.id}' + ",",
			},
			success : function(data) {
				if (data) {
					$.messager.alert("提示", "修改成功", "info");
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
		var id = 0;
		var sta = ""
		if (row.homeStaModel) {
			id = row.homeStaModel.id;
			sta = row.homeStaModel.status;
		}
		return "<a onclick='javascript:updateit(" + id + "," + row.id + ",\""
				+ sta + "\"" + ");'>自我评分</a>";
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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生作业</span>
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
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'time',width:180">日期</th>
				<th data-options="field:'title',width:230">作业标题</th>
				<th data-options="field:'scoreStandards',width:180">评分标准</th>
				<th
					data-options="field:'oneselfScore',width:100,formatter: function(value,row,index){
					if (row.homeStaModel){
					return row.homeStaModel.oneselfScore;
				}else{
				return '-';
				} 
			}">自我分数</th>
				<th
					data-options="field:'oneselfGrade',width:100,formatter: function(value,row,index){
					if (row.homeStaModel){
					return row.homeStaModel.oneselfGradeName;
				}else{
				return '-';
				} 
			}">自我评级</th>
				<th data-options="field:'remark1',width:230,formatter: function(value,row,index){
					if (row.homeStaModel){
					return row.homeStaModel.remark;
				}else{
				return '-';
				} 
			}">备注</th>
				<th data-options="field:'ww',width:100,formatter:ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>学生姓名:</label>
					</td>
					<td>
						<input type="text" name="homeworkId" id="homeworkId" hidden="true">
						<input type="text" name="id" id="id" hidden="true">
						<input id="userName" class="easyui-textbox" data-options="editable:false," style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">分数</label>
					</td>
					<td>
						<input id="score" name="oneselfScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>评级</label>
					</td>
					<td>
						<select class="easyui-combobox" name="oneselfGrade" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/homesta/getgradename.do'"
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
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
</body>
</html>