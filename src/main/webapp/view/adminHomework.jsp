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
			title : '作业修改',
			width : 650,
			height : 380,
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
					selectallstu();
				}
			}
		});
	}
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}
	var classId;
	function selectallstu() {
		classId = $("#class").combobox("getValue");
		$('#dg').datagrid({
			rownumbers:true,
			singleSelect : true,
			toolbar : '#tb',
			striped : true,
			fitColumns : true,
			fit : true,
			url : "<%=request.getContextPath()%>/homesta/selectallstu.do",
			queryParams : {
				classId : classId,
				time : $("#checktime").datebox("getValue"),
			},
			onLoadSuccess : function() {
				getclasssta(classId);
			},
		});
	}
	function updateit(id) {
		$('#Form').form("clear");
		$('#win').window("open");
		$("#id").val(id);
		if (id != 0) {
			$('#Form').form('load', '<%=request.getContextPath()%>/homesta/gethomework.do?id=' + id);
		}
	}
	function submitFun() {
		var url = "<%=request.getContextPath()%>/homesta/updatehomework.do";
		if ($("#id").val() == 0) {
			url = "<%=request.getContextPath()%>/homesta/inserthomework.do?classId=" + classId;
		}
		$('#Form').form({
			url : url,
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			success : function(data) {
				if (data == "update") {
					data = "修改成功!";
				} else if (data == "insert") {
					data = "添加成功!";
				} else {
					data = "服务器错误!"
				}
				$.messager.alert("提示", data, "info");
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

	function gethomesta(id) {
		location.href = "<%=request.getContextPath()%>/adminHomeSta.jsp"
	}

	var ff = function(value, row, index) {
		var id = row.id;
		return "<a onclick='javascript:updateit("
				+ id
				+ ");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/homesta/tohomesta.do?classId="
				+ classId + "&id=" + id + "'>作业详情</a>";
	}

	$.fn.datebox.defaults.cleanText = '清空';
	(function($) {
		var buttons = $.extend([], $.fn.datebox.defaults.buttons);
		buttons.splice(1, 0, {
			text : function(target) {
				return $(target).datebox("options").cleanText
			},
			handler : function(target) {
				$(target).datebox("setValue", "");
				$(target).datebox("hidePanel");
			}
		});
		$.extend($.fn.datebox.defaults, {
			buttons : buttons
		});
	})(jQuery);

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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生作业管理</span>
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
				&nbsp; &nbsp;&nbsp;日期:
				<input id="checktime" type="text" class="easyui-datebox" editable="false" value="" style="width: 150px; height: 30px;">

				<a href="#" class="easyui-linkbutton" onclick="selectallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 100px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="updateit(0)" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 27%;" data-options="plain:true,iconCls:'icon-add'">
					<span class="crmbuttonfont">新增作业</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'time',width:180,align:'center'">日期</th>
				<th data-options="field:'className',width:180,align:'center'">班级</th>
				<th data-options="field:'teacherName',width:230,align:'center'">讲师</th>
				<th data-options="field:'typeName',width:230,align:'center'">作业类型</th>
				<th data-options="field:'title',width:230">作业名称</th>
				<th data-options="field:'remark',width:230">备注</th>
				<th data-options="field:'ww',width:100,formatter:ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<br>
			<label style="margin-left: 56px;">作业日期: </label>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="id" id="id" hidden="true">
			<input id="time" name="time" class="easyui-datebox" data-options="required:true,editable:false" style="width: 150px; height: 30px;">
			<label style="margin-left: 52px;">作业类型:</label>
			<input id="type" name="type" class="easyui-combobox" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/homesta/gethomeworktype.do'"
				style="width: 150px; height: 30px;">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>作业标题:</label>
					</td>
					<td>
						<input id="title" name="title" class="easyui-textbox" data-options="required:true,multiline:true" style="width: 410px; height: 60px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>评分标准</label>
					</td>
					<td>
						<input id="scoreStandards" name="scoreStandards" class="easyui-textbox" data-options="required:true,multiline:true" style="width: 410px; height: 60px;">
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