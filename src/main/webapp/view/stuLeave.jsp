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
		 easyuiinit() ;
	});
	
	function easyuiinit() {
		$('#dg').datagrid({
			singleSelect : true,
			toolbar : '#tb',
			fit : true,
			fitColumns : true,
		});
		bgchang(0);
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
	function bgchang(num) {
		var radio = $("#" + num);
		radio.prop("checked", radio.is(":checked") ? false : true);
		$(":radio").each(
				function() {
					if ($(this).is(":checked")) {
						$(this).parent().parent().parent().css("background",
								"#009E94");
					} else {
						$(this).parent().parent().parent().css("background",
								"#ff9E94");
					}
				});
		$('#dg').datagrid({
			queryParams : {
				status : num == 0 ? '' : num,
			},
			url : "<%=request.getContextPath()%>/leave/selectStu.do"
		});
	}
	function addleave() {
		$('#win').window("open");
	}
	function submitFun() {
		$('#Form').form({
			url : '<%=request.getContextPath()%>/leave/insert.do',
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '申请表未填写完整!', "error");
				}
				return temp;
			},
			success : function(data) {
				if (data) {
					$.messager.alert('提示', '提交成功!', "info");
				}
				closeFun();
			}
		});
		$('#Form').submit();
	}
	function closeFun() {
		$('#win').window("close");
		$('#dg').datagrid("reload");
	}
	var ff = function(value, row, index) {
		var id = row.id;
		return "<a onclick='javascript:deleteit(" + id + ");'>删除</a>";
	}
</script>

<body>
	<div style="height: 80px; font-size: 15px;" id="tb">
		<div style="margin-top: 10px;">
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生请假查看</span>
			</div>
			<a id="btn" href="#" onclick="bgchang(0)" class="easyui-linkbutton" style="background: #ff9E94; width: 100px; height: 34px;" data-options="plain:true,selected:true,group:'select'">
				<input id="0" name="check" type="radio" hidden="true">
				<span class="crmbuttonfont">我的申请</span>
			</a>
			<a href="#" class="easyui-linkbutton" onclick="bgchang(1)" style="background: #ff9E94; width: 100px; height: 34px;" data-options="plain:true,group:'select'">
				<input id="1" name="check" type="radio" hidden="true">
				<span class="crmbuttonfont">审批中</span>
			</a>
			<a href="#" class="easyui-linkbutton" onclick="bgchang(5)" style="background: #ff9E94; width: 100px; height: 34px;" data-options="plain:true,group:'select'">
				<input id="5" name="check" type="radio" hidden="true">
				<span class="crmbuttonfont">申请已驳回</span>
			</a>
			<a href="#" class="easyui-linkbutton" onclick="bgchang(4)" style="background: #ff9E94; width: 100px; height: 34px;" data-options="plain:true,group:'select'">
				<input id="4" name="check" type="radio" hidden="true">
				<span class="crmbuttonfont">申请已通过</span>
			</a>
			<a href="#" class="easyui-linkbutton" onclick="addleave()" style="background: #009E94; width: 100px; height: 34px; float: right;" data-options="plain:true">
				<span class="crmbuttonfont">新建申请</span>
			</a>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'reason',width:200">原因</th>
				<th data-options="field:'time',width:200">申请时间</th>
				<th data-options="field:'startTime',width:200">开始时间</th>
				<th data-options="field:'endTime',width:200">截止时间</th>
				<th data-options="field:'remark',width:100">备注</th>
				<th data-options="field:'statusName',width:100">状态</th>
				<th data-options="field:'id',width:100,formatter: ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<span>开始日期:</span>
					</td>
					<td>
						<input name="startTime" type="text" class="easyui-datetimebox" editable="false" style="width: 380px; height: 30px;" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<span>截止日期:</span>
					</td>
					<td>
						<input name="endTime" type="text" class="easyui-datetimebox" editable="false" style="width: 380px; height: 30px;" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<span>原因:</span>
					</td>
					<td>
						<input name="reason" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 380px; height: 60px;">
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