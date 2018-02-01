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
		title : '作业批改',
		width : 650,
		height : 350,
		closed : true,//初始时是关闭状态
		cache : false,
		modal : true,
		doSize : true,
		border : 'thin',
		yIndex : 300,
		onClose : function() {
			$('#Form').form("clear");
			$("#className").textbox('textbox').css("color", "black");
			$("#userName").textbox('textbox').css("color", "black");
		}
	//模态
	});
	$('#class').combobox({
		editable : false,
		url : '<%=request.getContextPath()%>/rolePrem/getclass.do',
		valueField : 'id',
		textField : 'className',
		onLoadSuccess : function(data) {
			if (data) {
				var id = '${id}';
				var classId = '${classId}';
				$("#class").combobox("setValue", classId);
				$('#homeworktitle').combobox({
					url : '<%=request.getContextPath()%>/homesta/gethometitle.do',
					editable : false,
					valueField : 'id',
					textField : 'title',
					onLoadSuccess : function() {
						$("#homeworktitle").combobox("setValue", id);
						selectallstu();
					},
				});
			}
		},
		onChange : function(value) {
			$('#homeworktitle').combobox({
				queryParams : {
					classId : value,
				},
				onLoadSuccess : function() {
					$("#homeworktitle").combobox("setValue", "")
				}
			});
		}
	});
}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}
	var homeworkId;
	function selectallstu() {
		$('#dg').datagrid({
			rownumbers:true,
			toolbar : '#tb',
			checkOnSelect : false,
			striped : true,
			fitColumns : true,
			fit : true,
			url : "<%=request.getContextPath()%>/homesta/selectclasshomework.do",
			queryParams : {
				classId : $("#class").combobox("getValue"),
				id : $("#homeworktitle").combobox("getValue"),
			},
			onLoadSuccess : function() {
				$("#homeworkId").val($("#homeworktitle").combobox("getValue"));
				homeworkId = $("#homeworktitle").combobox("getValue");
			},
		});
	}
	var checkuserId = "";

	function updateit(id, userName, userId, status) {
		if (status == "1") {
			$.messager.alert("提示", "已锁定,不可修改!", "warning");
			return;
		}
		$('#win').window("open");
		if (id != 0) {
			$('#Form').form('load',
					'<%=request.getContextPath()%>/homesta/gethomeworksta.do?id=' + id);
		}
		$("#id").val(id);
		$("#homeworkId").val(homeworkId);
		$("#className").textbox("setValue", $("#class").combobox("getText"));
		$("#userName").textbox("setValue", userName);
		$("#oneselfScore").numberspinner({
			min : 0,
			max : 100,
		});
		$("#teacherScore").numberspinner({
			min : 0,
			max : 100,
		});
		checkuserId = "";
		checkuserId = userId + ","
	}

	function updateselect() {
		var rows = $("#dg").datagrid("getChecked");
		if (rows == '') {
			$.messager.alert("提示", "请选择需要修改的数据！", "warning");
			return;
		}
		var length = rows.length;
		checkuserId = "";
		for (var i = 0; i < length; i++) {
			checkuserId += rows[i].id + ",";
		}
		$('#win').window("open");
		$("#className").textbox("setValue", "选中的记录");
		$("#className").textbox('textbox').css("color", "red");
		$("#userName").textbox("setValue", "选中的记录");
		$("#userName").textbox('textbox').css("color", "red");
	}

	function closeFun() {
		$('#win').window("close");
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
				checkuserId : checkuserId,
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
	function homelock(id, status) {
		$.ajax({
			url : '<%=request.getContextPath()%>/homesta/lockhomesta.do',
			type : 'POST',
			data : {
				id : id,
				status : status,
			},
			dataType : 'text', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				if (data == "unlock") {
					$.messager.alert("提示", "锁定成功!", "info");
				} else if (data == "lock") {
					$.messager.alert("提示", "解锁成功!", "info");
				}
				$('#dg').datagrid('reload');
			},
		});
	}
	function goback() {
		window.history.back();
	}
	var ff = function(value, row, index) {
		var id = 0;
		var sta = "";
		var staname = "";
		var userName = row.trueName;
		if (row.homeStaModel) {
			id = row.homeStaModel.id;
			sta = row.homeStaModel.status;
			staname = row.homeStaModel.statusName;
		}
		return "<a onclick='javascript:updateit("
				+ id
				+ ",\""
				+ userName
				+ "\","
				+ row.id
				+ ",\""
				+ sta
				+ "\""
				+ ");'>修改</a>&nbsp;&nbsp;&nbsp;<a onclick='javascript:homelock("
				+ id + ",\"" + sta + "\"" + ")'>" + staname + "</a>";
	}
</script>
<body>
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生作业批阅</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<div style="margin-top: 10px; font-size: 15px; margin-left: 12%" id="content1" hidden="true">
				<div style="margin-top: 10px;">
					<table>
						<tr>
							<td>
								<label>班级:</label>
							</td>
							<td>
								<label style="border-bottom: 1px solid black; display: block; width: 100px;">${user.classId }</label>
							</td>
							<td>
								<label style="margin-left: 120px;">讲师:</label>
							</td>
							<td>
								<label style="border-bottom: 1px solid black; display: block; width: 100px;">${user.classId }</label>
							</td>
							<td>
								<label style="margin-left: 120px;">班级人数:</label>
							</td>
							<td>
								<label style="border-bottom: 1px solid black; display: block; width: 100px;">${user.classId }</label>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>

			<div style="margin-top: 10px; font-size: 15px; margin-left: 6%" id="content2">
				<span style="font-size: 20px;">作业批阅:</span>
				&nbsp; &nbsp;&nbsp;当前班级:
				<select id="class" name="class" style="width: 100px; height: 30px;">
				</select>
				&nbsp; &nbsp;&nbsp;试题列表:
				<input id="homeworktitle" style="width: 200px; height: 30px;">

				<a href="#" class="easyui-linkbutton" onclick="selectallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 40px" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="updateselect()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">批量修改</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="goback()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">返回</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'cb',checkbox:'true',align:'center'"></th>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'trueName',width:180,align:'center'">姓名</th>
				<th
					data-options="field:'oneselfScore',width:100,align:'center',formatter: function(value,row,index){
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
				<th
					data-options="field:'teacherScore',width:230,formatter: function(value,row,index){
					if (row.homeStaModel){
					return row.homeStaModel.teacherScore;
				}else{
 				return '-'; 
				} 
			}">讲师评分</th>
				<th
					data-options="field:'teacherGrade',width:230,align:'center',formatter: function(value,row,index){
					if (row.homeStaModel){
					return row.homeStaModel.teacherGradeName;
				}else{
 				return '-'; 
				} 
			}">讲师评级</th>
				<th data-options="field:'remark',formatter: function(value,row,index){
					if (row.homeStaModel){
					return row.homeStaModel.remark;
				}else{
 				return '-'; 
				} 
			},width:230">备注</th>
				<th data-options="field:'ww',width:100,formatter:ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<input type="text" name="homeworkId" id="homeworkId" hidden="true">
			<br>
			<input type="text" name="id" id="id" hidden="true">
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
						<input id="oneselfScore" name="oneselfScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">学生评级</label>
					</td>
					<td>
						<select class="easyui-combobox" name="oneselfGrade" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/homesta/getgradename.do'"
							style="width: 130px; height: 30px;">
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>讲师评分</label>
					</td>
					<td>
						<input id="teacherScore" name="teacherScore" class="easyui-numberspinner" required=true style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">讲师评级</label>
					</td>
					<td>
						<select class="easyui-combobox" name="teacherGrade" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/homesta/getgradename.do'"
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