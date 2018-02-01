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
			toolbar : '#tb',
			fit : true,
			checkOnSelect : false,
			fitColumns : true,
			striped : true,
			rownumbers:true,
		});
		$('#win').window({
			title : '请假申请表',
			width : 650,
			height : 370,
			closed : true,//初始时是关闭状态
			cache : false,
			modal : true,
			doSize : true,
			border : 'thin',
			yIndex : 300,
			onClose:function(){
			$('#Form').form("clear");
			$("#id").textbox('textbox').css("color", "black");
			$("#trueName").textbox('textbox').css("color", "black");
			$(".checkbox").each(function() {
			$(this).prop("hidden", true);
			$(this).prev().prev().textbox("enable");
			});
			}
		//模态
		});
		$('#leavewin').window({
			title : '请假申请表',
			width : 700,
			height : 250,
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
	
	var checktime;
	var classId;
	var checkuserId = "";
	var alluserId = "";

	function selectallstu() {
		checktime = $("#checktime").datebox("getValue");
		classId=$("#class").combobox("getValue");
		$('#dg').datagrid({
			url : "<%=request.getContextPath()%>/checkin/selectallStu.do",
			queryParams : {
				classId : classId,
				time : checktime
			},
			onLoadSuccess:function(data){
		     alluserId = "";
		    var rows = data.rows;
		    var length = rows.length;
		    for (var i = 0; i < length; i++) {
			 alluserId += rows[i].id + ",";
		     }
		    getclasssta(classId);
			},
		});
	}

	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
	}

	function updateit(idd, id, name) {
		$('#Form').form("clear");
		$('#win').window("open");
		if (idd != 0) {
			$('#Form').form('load', '<%=request.getContextPath()%>/checkin/getcheckin.do?id=' + idd);
		}
		$("#id").textbox("setValue", id);
		$("#trueName").textbox("setValue", name);
		checkuserId = "";
		checkuserId = id + ","
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
		$("#id").textbox("setValue", "选中的记录");
		$("#id").textbox('textbox').css("color", "red");
		$("#trueName").textbox("setValue", "选中的记录");
		$("#trueName").textbox('textbox').css("color", "red");
		$(".checkbox").each(function() {
			$(this).prop("hidden", false);
			$(this).prev().prev().textbox("disable");
			$(".checkbox").click(function() {
				if ($(this).is(':checked')) {
					$(this).prev().prev().textbox("enable");
				} else {
					$(this).prev().prev().textbox("disable");
				}
			})
		})
	}

	function closeFun() {
		$('#win').window("close");
	}

	function submitFun() {
		$('#Form').form({
			url : "<%=request.getContextPath()%>/checkin/updateStu.do",
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				return temp;
			},
			queryParams : {
				checktime : checktime,
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

	function cleanallstu() {
		var r = $.messager.confirm('确认', '确认清除今天所有学生的考勤?', function(r) {
			if (r == true) {
				$.ajax({
					url : '<%=request.getContextPath()%>/checkin/cleanallstu.do',
					type : 'POST',
					data : {
						checktime : checktime,
						cleanuserid : alluserId,
					},
					dataType : 'text', //返回的数据格式：json/xml/html/script/jsonp/text
					success : function(data) {
						if (data) {
							$.messager.alert("提示", "清除成功", "info");
							closeFun();
							$('#dg').datagrid('reload');
						}
					},
				});
			}
		});
	}

	function leaveallstu() {
		$('#leavewin').window("open");
		$('#leavedg').datagrid({
			queryParams : {
				time : checktime,
				classId : classId,
			},
			url : "<%=request.getContextPath()%>/leave/selectallStu.do"
		});
	}

	var ff = function(value, row, index) {
		var idd = 0;
		var id = row.id;
		var name = row.trueName
		if (row.checkinModel) {
			idd = row.checkinModel.id;
		}
		return "<a onclick='javascript:updateit(" + idd + "," + id + ",\"" + name
				+ "\""+");'>修改</a>";
	}

	var fff = function(value, row, index) {
		if (row.checkinModel) {
			return row.checkinModel.time;
		} else {
			return checktime;
		}
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
	function printit() {
		$.messager.confirm("操作提示", "您确定要打印吗？", function (data) {
            if (data) {
            	window.print();//打印
            }
            else {
            }
        });
	}
</script>
<body>
	<div id="tb">
		<div>
			<div style="height: 30px; margin-top: 10px;">
				<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">学生考勤管理</span>
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
				<input id="checktime" type="text" class="easyui-datebox" editable="false" value="now()" style="width: 150px; height: 30px;" required="required">

				<a href="#" class="easyui-linkbutton" onclick="selectallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 100px;" data-options="plain:true,iconCls:'icon-search'">
					<span class="crmbuttonfont">查询</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="updateselect()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-edit'">
					<span class="crmbuttonfont">批量修改</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="cleanallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-tip'">
					<span class="crmbuttonfont">清除考勤</span>
				</a>
				<a href="#" class="easyui-linkbutton" onclick="leaveallstu()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-man'">
					<span class="crmbuttonfont">请假记录</span>
				</a>
				<a href="#"  class="easyui-linkbutton" onclick="printit()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;" data-options="plain:true,iconCls:'icon-man'">
					<span class="crmbuttonfont">打印</span>
				</a>
			</div>
			<br>
		</div>
	</div>
	<input type="text" id="time" hidden="true">
	<table id="dg">
		<thead>
			<tr>
				<th data-options="field:'cb',checkbox:'true',align:'center'"></th>
				<th data-options="field:'id'" hidden="true"></th>
				<th data-options="field:'time',width:80,align:'center',formatter: fff,align:'center'">日期</th>
				<th data-options="field:'trueName',width:100,align:'center'">学生姓名</th>
				<th
					data-options="field:'onecheck',width:80,align:'center',formatter: function(value,row,index){
					if (row.checkinModel){
					return row.checkinModel.onecheckName;
				} else{
				return '-';
				}
			}">早晨</th>
				<th
					data-options="field:'twocheck',align:'center',width:80,formatter: function(value,row,index){
					if (row.checkinModel){
					return row.checkinModel.twocheckName;
				} else{
				return '-';
				}
			}">午饭前</th>
				<th
					data-options="field:'threecheck',align:'center',width:80,formatter: function(value,row,index){
					if (row.checkinModel){
					return row.checkinModel.threecheckName;
				} else{
				return '-';
				}
			}">午饭后</th>
				<th
					data-options="field:'fourcheck',align:'center',width:80,formatter: function(value,row,index){
					if (row.checkinModel){
					return row.checkinModel.fourcheckName;
				} else{
				return '-';
				}
			}">晚饭前</th>
				<th
					data-options="field:'fivecheck',align:'center',width:80,formatter: function(value,row,index){
					if (row.checkinModel){
					return row.checkinModel.fivecheckName;
				} else{
				return '-';
				}
			}">晚饭后</th>
				<th
					data-options="field:'sixcheck',align:'center',width:80,formatter: function(value,row,index){
					if (row.checkinModel !=null){
					return row.checkinModel.sixcheckName;
				}else{
				return '-';
				} 
			}">晚自习</th>
				<th
					data-options="field:'remark',align:'center',width:80,formatter: function(value,row,index){
					if (row.checkinModel){
					return row.checkinModel.remark;
				} else{
				return '-';
				}
			}">备注</th>
				<th data-options="field:'ids',align:'center',width:80,formatter: ff">操作</th>
			</tr>
		</thead>
	</table>
	<div id="win">
		<form id="Form" method="post">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>学生编号:</label>
					</td>
					<td>
						<input id="id" class="easyui-textbox" data-options="required:true,editable:false" style="width: 130px; height: 30px;">
					</td>
					<td>
						<label style="margin-left: 60px;">学生姓名:</label>
					</td>
					<td>
						<input id="trueName" class="easyui-textbox" data-options="required:true,editable:false" style="width: 130px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>早晨:</label>
					</td>
					<td>
						<select class="easyui-combobox" name="onecheck" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/checkin/getchecksta.do'"
							style="width: 130px; height: 30px;">
						</select>
						<input type="checkbox" class="checkbox" hidden="true">
					</td>

					<td>
						<label style="margin-left: 60px;">午饭前:</label>
					</td>
					<td>
						<select class="easyui-combobox" name="twocheck" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/checkin/getchecksta.do'"
							style="width: 130px; height: 30px;">
						</select>
						<input type="checkbox" class="checkbox" hidden="true">
					</td>
				</tr>
				<tr>
					<td>
						<label>午饭后:</label>
					</td>
					<td>
						<select class="easyui-combobox" name="threecheck" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/checkin/getchecksta.do'"
							style="width: 130px; height: 30px;">
						</select>
						<input type="checkbox" class="checkbox" hidden="true">
					</td>
					<td>
						<label style="margin-left: 60px;">晚饭前:</label>
					</td>
					<td>
						<select class="easyui-combobox" name="fourcheck" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/checkin/getchecksta.do'"
							style="width: 130px; height: 30px;">
						</select>
						<input type="checkbox" class="checkbox" hidden="true">
					</td>
				</tr>
				<tr>
					<td>
						<label>晚饭后:</label>
					</td>
					<td>
						<select class="easyui-combobox" name="fivecheck" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/checkin/getchecksta.do'"
							style="width: 130px; height: 30px;">
						</select>
						<input type="checkbox" class="checkbox" hidden="true">
					</td>
					<td>
						<label style="margin-left: 60px;">晚自习:</label>
					</td>
					<td>
						<select class="easyui-combobox" name="sixcheck" data-options="required:true,editable:false,valueField:'code',textField:'name',url:'<%=request.getContextPath()%>/checkin/getchecksta.do'"
							style="width: 130px; height: 30px;">
						</select>
						<input type="checkbox" class="checkbox" hidden="true">
					</td>
				</tr>
			</table>
			<div>
				<label style="margin-left: 60px;">备注:</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="remark" class="easyui-textbox" data-options="multiline:true" style="width: 410px; height: 60px;">
				<input type="checkbox" class="checkbox" hidden="true">
			</div>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
	<div id="leavewin">
		<table id="leavedg">
			<thead>
				<tr>
					<th data-options="field:'userName',width:80">学生</th>
					<th data-options="field:'reason',width:130">原因</th>
					<th data-options="field:'time',width:150">申请时间</th>
					<th data-options="field:'startTime',width:150">开始时间</th>
					<th data-options="field:'endTime',width:150">截止时间</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>