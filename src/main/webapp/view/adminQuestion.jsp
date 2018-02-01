<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/crm.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/demo.css">
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
		title : '问卷修改',
		width : 500,
		height : 530,
		closed : true,//初始时是关闭状态
		cache : false,
		modal : true,
		doSize : true,
		border : 'thin',
		yIndex : 300,
	//模态
	});
	$('#addquestion').window({
		title : '试题',
		width : 600,
		height : 440,
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
	$('#allquestiondl').datalist({
		checkbox : true,
		lines : true,
		striped : true,
		fit : true,
		border : false,
		singleSelect : false,
		valueField : "id",
		textField : "title",
	});
	$('#questiondl').datalist({
		lines : true,
		striped : true,
		fit : true,
		border : false,
		singleSelect : false,
		valueField : "id",
		textField : "title",
	});
	$('#hasquestiondl').datalist({
		lines : true,
		striped : true,
		fit : true,
		border : false,
		singleSelect : false,
		valueField : "id",
		textField : "title",
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
			url : "<%=request.getContextPath()%>/question/getclassquestion.do",
			queryParams : {
				classId : classId,
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
		if (!id) {
			return;
		}
		$('#Form').form('load', '<%=request.getContextPath()%>/question/getquestion.do?id=' + id);
		$('#hasquestiondl').datalist({
			url : '<%=request.getContextPath()%>/questionQa/gethasquestionqa.do?id=' + id,
		});
	}

	function submitFun() {
		var strid = $("#strid").val();
		if (!$("#id").val()) {
			if (!strid) {
				$.messager.alert('提示', '请选择题目!', "error");
				return;
			}
		}
		$('#Form').form({
			url : "<%=request.getContextPath()%>/question/updatequestion.do",
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			queryParams : {
				classId : classId,
				strId : strid
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

	function closeFun() {
		$('#win').window("close");
	}

	function closeaddFun() {
		$('#addquestion').window("close");
	}

	var ff = function(value, row, index) {
		var id = row.id;
		return "<a onclick='javascript:updateit("
				+ id
				+ ");'>修改</a>&nbsp;&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/questionanswer/toquestionanswer.do?classId="
				+ classId + "&id=" + id + "'>问卷详情</a>";
	}

	// 	addquestion
	function addquestionqa() {
		$('#addquestion').window("open");
		$('#allquestiondl').datalist({
			url : '<%=request.getContextPath()%>/questionQa/getquestionqa.do',
		});
		$('#questiondl').datalist(
				{
					url : '<%=request.getContextPath()%>/questionQa/gethasquestionqa.do?id='
							+ $("#id").val(),
				});
	}

	function add() {
		var lengthtemp = $('#questiondl').datalist('getData').rows.length;
		if (lengthtemp == 7) {
			$.messager.alert("提示", "试题已达到7个,请先清空!", "info");
			return;
		}
		var rows = $('#allquestiondl').datalist("getChecked");
		var length = rows.length;
		if (length != 7) {
			$.messager.alert("提示", "请选择7道题目!", "info");
			return;
		}
		for (var i = 0; i < length; i++) {
			var row = rows[i];
			var idd = row.id;
			var title = row.title;
			$('#questiondl').datalist("appendRow", {
				id : idd,
				title : title,
			});
		}
		$('#allquestiondl').datalist({
			rowStyler : function(index, row) {
				var length = rows.length;
				for (var i = 0; i < length; i++) {
					if (row.id == rows[i].id) {
						return 'display:none'; // return inline style 
					}
				}
			}
		});
	}

	function remove() {
		$('#questiondl').datalist('loadData', {
			total : 0,
			rows : []
		});
		$('#allquestiondl').datalist({
			rowStyler : function(index, row) {
				return 'display:false'; // return inline style 
			}
		});
	}

	function submitaddFun() {
		var data = $('#questiondl').datalist("getData");
		var rows = data.rows;
		var length = rows.length;
		if (length != 7) {
			$.messager.alert("提示", "请选择7道题目!", "info");
			return;
		}
		strid = "";
		for (var i = 0; i < length; i++) {
			strid += rows[i].id + ",";
		}
		$("#strid").val(strid);
		var id = $("#id").val();
		if (!id) {
			$("#hasquestiondl").datalist("loadData", data);
			closeaddFun();
			return;
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/questionQa/updatequesrel.do',
			type : 'POST',
			data : {
				id : id,
				strid : strid,
			},
			dataType : 'text', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				if (data) {
					$.messager.alert("提示", "修改成功!", "info");
				}
				closeaddFun();
				$('#hasquestiondl').datalist("reload");
			},
		});
	}

	function addquestion() {
		$('#Form').form("clear");
		$('#win').window("open");
		$('#hasquestiondl').datalist('loadData', {
			total : 0,
			rows : []
		});
		$('#questiondl').datalist('loadData', {
			total : 0,
			rows : []
		});
		$('#allquestiondl').datalist({
			url : '<%=request.getContextPath()%>/questionQa/getquestionqa.do',
		});
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
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">问卷管理</span>
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

				<a href="#" class="easyui-linkbutton" onclick="selectallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 100px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="addquestion()" style="background: #E0ECFF; width: 100px; height: 31px; float: right;" data-options="plain:true,iconCls:'icon-add'">
					<span class="crmbuttonfont">新增问卷</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'time',width:230,align:'center'">日期</th>
				<th data-options="field:'questionName',width:180">问卷标题</th>
				<th data-options="field:'userId',width:230,align:'center'">讲师</th>
				<th data-options="field:'ww',width:100,formatter:ff,align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<br>
			<label style="margin-left: 56px;">发布日期: </label>
			&nbsp;&nbsp;&nbsp;
			<input type="text" name="id" id="id" hidden="true">
			<input type="text" name="strid" id="strid" hidden="true">
			<input name="time" class="easyui-datebox" data-options="required:true,editable:false" style="width: 150px; height: 30px;">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>问卷标题:</label>
					</td>
					<td>
						<input name="questionName" class="easyui-textbox" data-options="required:true,multiline:true" style="width: 250px; height: 60px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>问卷试题</label>
					</td>
					<td>
						<div style="width: 300px; height: 150px;">
							<div id="hasquestiondldiv" style="border: 1px solid #ccc; width: 230px; height: 280px; float: left;">
								<ul id="hasquestiondl" required=true>
								</ul>
							</div>
							<a class="easyui-linkbutton" onclick="addquestionqa()" style="background: rgb(255, 158, 148); width: 60px; height: 34px; margin-left: 8px; margin-top: 70px;" data-options="plain:true">更改试题</a>
						</div>
					</td>
				</tr>
			</table>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 26%;" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
	<div id="addquestion">
		<table>
			<tr>
				<td>
					<br>
					<label style="margin-left: 50px; font-size: 20px; color: rgb(26, 51, 243)">所有题目:</label>
					<br>
					<div style="border: 1px solid #ccc; width: 230px; height: 280px; float: left; margin-left: 20px; margin-top: 20px;">

						<ul id="allquestiondl">

						</ul>
					</div>
				</td>
				<td>
					<a class="easyui-linkbutton" onclick="add()" style="background: rgb(255, 158, 148); width: 60px; height: 34px; margin-left: 8px; margin-top: 70px;" data-options="plain:true">添加->></a>
					<br>
					<a class="easyui-linkbutton" onclick="remove()" style="background: rgb(255, 158, 148); width: 60px; height: 34px; margin-left: 8px; margin-top: 70px;" data-options="plain:true">&lt;&lt;-清空</a>
				</td>
				<td>
					<br>
					<label style="margin-left: 50px; font-size: 20px; color: rgb(26, 51, 243)">已添加:</label>
					<div id="questiondldiv" style="border: 1px solid #ccc; width: 230px; height: 280px; float: right; margin-right: 20px; margin-top: 20px;">
						<ul id="questiondl">
						</ul>
					</div>
				</td>
			</tr>
		</table>
		<br>
		<a class="easyui-linkbutton" onclick="submitaddFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 26%;" data-options="plain:true">保存</a>
		<a href="#" class="easyui-linkbutton" onclick="closeaddFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
	</div>
</body>
</html>