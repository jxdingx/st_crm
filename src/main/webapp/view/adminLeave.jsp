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
		width : 400,
		height : 180,
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
		var time = $("#checktime").datebox("getValue");
		if (!classId) {
			return;
		}
		$('#dg').datagrid({
			rownumbers:true,
			singleSelect : true,
			toolbar : '#tb',
			striped : true,
			fit : true,
			url : "<%=request.getContextPath()%>/leave/selectallStu.do",
			queryParams : {
				classId : classId,
				time : time,
			},
			onLoadSuccess : function() {
				getclasssta(classId);
			},
		});
	}
	function selectall() {
		$("#class").combobox("clear");
		$("#checktime").datebox("clear");
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/leave/selectallleave.do",
		})
	}

	function updateit(id) {
		$(':input[type="checkbox"]').prop('checked', false);
		$('#win').window("open");
		$("#id").val(id);
	}

	function submitFun(pageNumber, pageSize) {
		var value = $(':input[type="checkbox"]:checked').val();
		if (!value) {
			closeFun();
			return;
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/leave/approve.do',
			type : 'POST',
			data : {
				id : $("#id").val(),
				value : value,
				pageNumber : pageNumber,
				pageSize : pageSize
			},
			dataType : 'text', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				if (data) {
					closeFun();
					$.messager.alert("提示", "修改成功!", "info");
					$('#dg').datagrid('reload');
				}
			},
		});
	}

	function closeFun() {
		$('#win').window("close");
	}

	var ff = function(value, row, index) {
		var id = row.id;
		return "<a onclick='javascript:updateit(" + id + ");'>审批</a>";
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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生请假管理</span>
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
				<a href="#" class="easyui-linkbutton" onclick="selectall(1,3)" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-search'">
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
				<th data-options="field:'className',width:80,align:'center'">班级</th>
				<th data-options="field:'userName',width:100",align:'center'>学生姓名</th>
				<th data-options="field:'reason',width:200">原因</th>
				<th data-options="field:'time',width:200">申请时间</th>
				<th data-options="field:'startTime',width:200">开始时间</th>
				<th data-options="field:'endTime',width:200">截止时间</th>
				<th data-options="field:'remark',width:100">备注</th>
				<th data-options="field:'statusName',width:100,align:'center'">状态</th>
				<th data-options="field:'ids',width:100,formatter: ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<br>
		<input type="text" name="id" id="id" hidden="true">
		<div style="font-size: 20px;">
			<label style="margin-left: 20%">申请同意:</label>
			<input type="checkbox" value="1" style="width: 20px; height: 20px; margin-top: 10px;">
			<label style="margin-left: 15px;">申请驳回:</label>
			<input type="checkbox" value="0" style="width: 20px; height: 20px; margin-top: 10px;">
		</div>
		<br>
		<br>
		<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 20%" data-options="plain:true">保存</a>
		<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
	</div>
</body>
</html>