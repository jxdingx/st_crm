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
		selectallstu();
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
	//模态
	});
}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function selectallstu() {
		var id = "${id}";
		var classId = '${classId}';
		$('#dg').datagrid({
			rownumbers:true,
			toolbar : '#tb',
			checkOnSelect : false,
			striped : true,
			fitColumns : true,
			fit : true,
			url : "<%=request.getContextPath()%>/questionanswer/getquestionanswer.do",
			queryParams : {
				classId : classId,
				id : id,
			},
		});
	}
</script>
<body>
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生作业批阅</span>
			</div>
			<label onclick="checkhidden('content1')">查看内容 ▼</label>
			<br>
		</div>
		<div>
			<label onclick="checkhidden('content2')">查询内容 ▼</label>

			<div style="margin-top: 10px; font-size: 15px; margin-left: 6%" id="content2"></div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id',width:180" hidden="true">学号</th>
				<th data-options="field:'trueName',width:180,align:'center'">姓名</th>
				<th
					data-options="field:'answer1',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer1;
				}else{
				return '-';
				} 
			}">第一题</th>
				<th
					data-options="field:'answer2',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer2;
				}else{
				return '-';
				} 
			}">第二题</th>
				<th
					data-options="field:'answer3',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer3;
				}else{
				return '-';
				} 
			}">第三题</th>
				<th
					data-options="field:'answer4',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer4;
				}else{
				return '-';
				} 
			}">第四题</th>
				<th
					data-options="field:'answer5',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer5;
				}else{
				return '-';
				} 
			}">第五题</th>
				<th
					data-options="field:'answer6',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer6;
				}else{
				return '-';
				} 
			}">第六题</th>
				<th
					data-options="field:'answer7',width:100,align:'center',formatter: function(value,row,index){
					if (row.questionAnswerModel){
					return row.questionAnswerModel.answer7;
				}else{
				return '-';
				} 
			}">第七题</th>
			</tr>
		</thead>
	</table>
</body>
</html>