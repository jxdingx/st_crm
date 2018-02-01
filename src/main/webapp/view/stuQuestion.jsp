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
<style type="text/css">
.viewlist {
	font-size: 16px;
	margin-top: 5px;
	margin-bottom: 5px;
	padding-left: 40px;
	border: 2px solid #E0ECFF;
	border-right-color: white;
	border-top-color: white;
	border-left-color: white;
}

.title {
	font-size: 15px;
	width: 150px;
}

.opt_mark {
	width: 50px;
	height: 20px;
	background-color: #3F3;
	color: #FFF;
	cursor: pointer;
	text-align: center;
	border: solid #eee 1px;
	padding: 0px 10px 0px 10px;
}

.opt_mark_selected {
	background-color: #F33;
}
</style>
</head>
<script type="text/javascript">
	$(function() {
		easyuiinit();
	});
	
	function easyuiinit() {
		$('#win').window({
			title : '问卷回答',
			width : 600,
			height : 550,
			closed : true,//初始时是关闭状态
			cache : false,
			modal : true,
			doSize : true,
			border : 'thin',
			yIndex : 300,
			onClose : function() {
				$("#Form").form("clear");
				$("#id").val(0);
			}
		//模态
		});
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
		$(document).on("click", ".opt_mark", function(e) {
			var o = $(e.target);
			o.addClass("opt_mark_selected");
			o.siblings().removeClass("opt_mark_selected");
			var answer = o.parent().parent().parent().next();
			answer.val(o.text())
		});
	}
	function selectstu() {
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/question/selectStu.do",
		});
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	var ff = function(value, row) {
		var id = row.id;
		return "<a onclick='javascript:answerques(" + id + ");'>回答</a>";
	}

	function answerques(id) {
		$("#creatQuestionId").val(id);
		$.ajax({
			url : '<%=request.getContextPath()%>/questionQa/gethasquestionqa.do',
			type : 'POST',
			data : {
				id : id,
			},
			dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				showquestion(data, id);
			},
		});
	}

	function showquestion(rows, id) {
		var str = "";
		for (var i = 1; i < 8; i++) {
			var str1 = '<div class="viewlist"><b>第' + i
					+ '题</b><div style="margin-left: 100px;"><b>'
					+ rows[i - 1].title + '</b></div><br><br>'
			var str2 = '<table style="position: relative;margin-left: 20px;margin-top: -25px;" cellspacing="10"><tbody><tr><td width="60px"><label class="title"><b>选项：</b></label></td>'
			var str3 = '<td><span>A.</span><span>' + rows[i - 1].optionsA
					+ '</span></td><td><span>B.</span><span>'
					+ rows[i - 1].optionsB
					+ ' </span></td><td><span>C.</span><span>'
					+ rows[i - 1].optionsC
					+ ' </span></td><td><span>D.</span><span>'
					+ rows[i - 1].optionsD + ' </span></td></tr>'
			var str4 = '<tr><td><label class="title"><b>回答：</b></label></td><td class="opt_mark">A</td><td class="opt_mark">B</td><td class="opt_mark">C</td><td class="opt_mark">D</td></tr></tbody></table>'
			var str5 = '<input id="answer'+i+'" name="answer'+i+'" type="hidden"></div>';
			str += str1 + str2 + str3 + str4 + str5;
		}
		$("#questionqa").html(str);
		showquesanswer(id);
	}
	function showquesanswer(id) {
		$.ajax({
			url : '<%=request.getContextPath()%>/questionanswer/getquesanswer.do',
			type : 'POST',
			data : {
				id : id,
			},
			dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				if (data[0]) {
					$("#id").val(data[0].id);
					var ansstr = data[0].answer.split(",");
					for (var i = 1; i < 8; i++) {
						var value = ansstr[i - 1];
						var answer = $("#answer" + i);
						answer.val(value);
						var op = answer.prev().find("[class='opt_mark']");
						op.eq(0).siblings().removeClass("opt_mark_selected");
						if (value == 'A') {
							op.eq(0).addClass("opt_mark_selected")
						} else if (value == 'B') {
							op.eq(1).addClass("opt_mark_selected")
						} else if (value == 'C') {
							op.eq(2).addClass("opt_mark_selected")
						} else if (value == 'D') {
							op.eq(3).addClass("opt_mark_selected")
						}
					}
				}
			},
		});
		$('#win').window("open");
	}
	function closeFun() {
		$('#win').window("close");
	}
	function submitFun() {
		var answer = "";
		for (var i = 1; i < 8; i++) {
			var ans = $("#answer" + i).val();
			if (!ans) {
				$.messager.alert("提示", "请填写完整!", "info");
				return;
			}
			answer += ans + ",";
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/questionanswer/updatequesanswer.do',
					type : 'POST',
					data : {
						creatQuestionId : $("#creatQuestionId").val(),
						id : $("#id").val(),
						answer : answer,
					},
					dataType : 'text', //返回的数据格式：json/xml/html/script/jsonp/text
					success : function(data) {
						$.messager.alert("提示", "回答成功!", "info");
						closeFun();
					},
				});
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
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'time',width:50,align:'center'">周次</th>
				<th data-options="field:'questionName',width:80,align:'center'">名称</th>
				<th data-options="field:'classId',width:80,align:'center'">班级</th>
				<th data-options="field:'remark',width:150,align:'center'">备注</th>
				<th data-options="field:'s',width:80,formatter:ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<input name="creatQuestionId" id="creatQuestionId" type="hidden">
			<input name="id" id="id" value="0" type="hidden">
			<div id="questionqa"></div>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
			<br>
			<br>
		</form>
	</div>
</body>
</html>