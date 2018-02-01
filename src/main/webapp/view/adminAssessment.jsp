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
	//easyui控件初始化
	function easyuiinit() {
		$('#win').window({
			title : '申请批准',
			width : 600,
			height : 400,
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
			url : '<%=request.getContextPath()%>/rolePrem/getclass.do',
			valueField : 'id',
			textField : 'className',
			onLoadSuccess : function(data) {
				if (data) {
					$('#class').combobox('setValue', data[0].id);
					getAssessment();
				}
			}
		});
		$('#dg').datagrid({
			singleSelect : true,
			toolbar : '#tb',
			striped : true,
			fitColumns : true,
			fit : true,
			rownumbers:true,
		});
		$(".easyui-numberspinner").numberspinner({
			min : 0,
			max : 100,
		});
	}
	
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}
	
	var classId = "";
	var time = "";
	function getAssessment() { 
		classId = $("#class").combobox("getValue");
		year = $("#year").combobox("getValue");
		month = $("#month").combobox("getValue");
		time = year + "-" + month;
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/assessment/getAssessment.do",
			queryParams : {
				classId : classId,
				time : time
			},
			onLoadSuccess : function() {
				getclasssta(classId);
			},
		});
	}

	function updateit(id, userId, trueName) {
		$('#Form').form("clear");
		$('#win').window("open");
		$("#id").val(id);
		$("#time").val(time);
		$("#userId").val(userId);
		$("#className").textbox("setValue", $("#class").combobox("getText"));
		$("#userName").textbox("setValue", trueName);
		if (id != 0) {
			$('#Form').form('load',
					'<%=request.getContextPath()%>/assessment/getassessStu.do?id=' + id);
		}
	}
	
	function closeFun() {
		$('#win').window("close");
	}

	function submitFun() {
		$('#Form').form({
			url : '<%=request.getContextPath()%>/assessment/updatestu.do',
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


	function assessmentDown() {
		window.location.href = "<%=request.getContextPath()%>/assessment/assessmentDown.do?classId="
				+ classId + "&time=" + time;
		return;
	}

	var ff = function(value, row, index) {
		var id = 0;
		if (row.assessmentModel) {
			id = row.assessmentModel.id;
		}
		var className = row.className;
		var trueName = row.trueName;
		var userId = row.id;
		return "<a onclick='javascript:updateit(" + id + "," + userId + ",\""
				+ trueName + "\"" + ");'>评分</a>";
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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">月度考核</span>
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
				&nbsp; &nbsp;&nbsp;考核时间:
				<select class="easyui-combobox" id="year" data-options="editable:false" style="width: 100px; height: 30px;">
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
				</select>
				<select class="easyui-combobox" id="month" data-options="editable:false" style="width: 100px; height: 30px;">
					<option value="01">1月</option>
					<option value="02">2月</option>
					<option value="03">3月</option>
					<option value="05">5月</option>
					<option value="06">6月</option>
					<option value="07">7月</option>
					<option value="08">8月</option>
					<option value="09">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>
				<a href="#" class="easyui-linkbutton" onclick="getAssessment()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 200px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" onclick="assessmentDown()" class="easyui-linkbutton" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 25px;" data-options="plain:true,iconCls:'icon-print'">
					<span class="crmbuttonfont">excel下载</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'trueName',width:120,align:'center'">学生姓名</th>
				<th
					data-options="field:'ranking',width:220,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.ranking;
				}else{
				return '-';
				}
				}">排名</th>
				<th
					data-options="field:'score',width:100,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.score;
				}else{
				return '-';
				}
				}">总分</th>
				<th
					data-options="field:'checkScore',width:100,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.checkScore;
				}else{
				return '-';
				}
				}">考勤分</th>
				<th
					data-options="field:'homeworkScore',width:100,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.homeworkScore;
				}else{
				return '-';
				}
				}">作业分</th>
				<th
					data-options="field:'projectScore',width:100,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.projectScore;
				}else{
				return '-';
				}
				}">项目分</th>
				<th
					data-options="field:'dailyScore',width:100,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.dailyScore;
				}else{
				return '-';
				}
				}">平时成绩</th>
				<th
					data-options="field:'remark',width:250,align:'center',formatter:function(value,row){
				if(row.assessmentModel){
				return row.assessmentModel.remark;
				}else{
				return '-';
				}
				}">备注</th>
				<th data-options="field:'ids',width:100,formatter: ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<input type="text" name="userId" id="userId" hidden="true">
			<input type="text" name="id" id="id" hidden="true">
			<input type="text" name="time" id="time" hidden="true">
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
						<input id="userName" name="userName" class="easyui-textbox" data-options="editable:false," style="width: 130px; height: 30px;">
					</td>
				</tr>

				<tr>
					<td>
						<label>排名</label>
					</td>
					<td>
						<input id="ranking" name="ranking" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">总分</label>
					</td>
					<td>
						<input id="score" name="score" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
				</tr>

				<tr>
					<td>
						<label>考勤分</label>
					</td>
					<td>
						<input id="checkScore" name="checkScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>

					<td>
						<label style="margin-left: 60px;">作业分</label>
					</td>
					<td>
						<input id="homeworkScore" name="homeworkScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>项目分</label>
					</td>
					<td>
						<input id="projectScore" name="projectScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">平时成绩</label>
					</td>
					<td>
						<input id="dailyScore" name="dailyScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
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