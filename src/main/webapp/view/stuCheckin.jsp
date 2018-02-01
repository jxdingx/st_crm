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
		easyuiinit()
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

	}
	function selectstu() {
		var time = $("#name").combobox("getValue");
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/checkin/selectStu.do",
			queryParams : {
				time : time,
			},
		});
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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生考勤查看</span>
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
				&nbsp; &nbsp;&nbsp;周次:
				<select class="easyui-combobox" id="name" name="time" data-options="editable:false" style="width: 250px;">
					<option value="2018-01-10,2018-01-17">第一周2018-01-10日至2018-01-17日</option>
					<option value="2018-01-18,2018-01-25">第二周2018-01-18日至2018-01-25日</option>
					<option value="2018-01-26,2018-02-02">第三周2018-01-26日至2018-02-02日</option>
					<option value="2018-02-03,2018-02-10">第四周2018-02-03日至2018-02-10日</option>
					<option value="2018-02-11,2018-02-18">第五周2018-02-11日至2018-02-18日</option>
				</select>
				&nbsp;
				<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="selectstu()"></a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'time',width:200">日期</th>
				<th data-options="field:'onecheckName',width:80">早晨</th>
				<th data-options="field:'twocheckName',width:80">午饭前</th>
				<th data-options="field:'threecheckName',width:80">午饭后</th>
				<th data-options="field:'fourcheckName',width:80">晚饭前</th>
				<th data-options="field:'fivecheckName',width:80">晚饭后</th>
				<th data-options="field:'sixcheckName',width:80">晚自习</th>
				<th data-options="field:'score',width:80">成绩</th>
				<th data-options="field:'remark',width:80">备注</th>
			</tr>
		</thead>
	</table>
</body>
</html>