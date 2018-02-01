<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/themes/icon.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/easyui-lang-zh_CN.js"></script>
<title>客户关系管理系统主页</title>
<style type="text/css">
.main_a {
	font-size: 16px;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(function() {
		var userId = '${user.id}';
		// 		alert(userId);
		if (!userId) {
			window.location.href = "<%=request.getContextPath()%>/view/login.jsp";
		}

		getusermenu();

	});

	function getusermenu() {
		$.ajax({
			url : '<%=request.getContextPath()%>/user/getusermenu.do',
			type : 'POST',
			dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
			success : function(data) {
				menu(data);
				success();
			}
		});
	}

	function menu(data) {
		// 		alert(JSON.stringify(data.two));
		if (data) {
			var one = data.one;
			var onelength = one.length;
			var two = data.two;
			var twolength = two.length;
			var str = '<div class="easyui-accordion" data-options="border:false,height:350">';
			for (var i = 0; i < onelength; i++) {
				var str1 = '<div title="'+one[i].menuName+'" data-options="iconCls:\'icon-yxgl\'"  style="padding: 10px">'
				var str2 = "";
				for (var j = 0; j < twolength; j++) {
					if (two[j].menuParentCode == one[i].menuCode) {
						str2 += '<a href="javascript:add(\''
								+ two[j].menuName
								+ '\','
								+ '\''
								+ two[j].menuUrl
								+ '\')" class="easyui-linkbutton" data-options="plain:true,iconCls:\'icon-ok\'">'
								+ two[j].menuName + '</a><br>'
					}
				}
				var str3 = "</div>"
				str += str1 + str2 + str3;
			}
			$("#usermenu").html(str + "</div>");
			$.parser.parse($("#usermenu"));
		}
	}
	function success() {
		$('#win').window({
			title : '密码修改',
			width : 350,
			height : 300,
			closed : true,//初始时是关闭状态
			cache : false,
			modal : true,
			doSize : true,
			border : 'thin',
			yIndex : 300,
			onClose : function() {
				$('#Form').form("clear");
			}
		//模态
		});
		$('#explain').window({
			title : '密码修改',
			width : 400,
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

	function add(TitleText, src) {
		if ($('#tt').tabs("exists", TitleText)) {
			$('#tt').tabs("select", TitleText)

		} else {
			var content = "<iframe scrolling='no' frameborder='no'  style='width: 100%;height: 100%;' src='${pageContext.request.contextPath}/view/"
					+ src + "'></iframe>"
			$('#tt').tabs('add', {
				title : TitleText,
				content : content,
				closable : true,
				tools : [ {
					iconCls : 'icon-reload',
					handler : function() {
						var currTab = $('#tt').tabs('getSelected');
						var content = $(currTab.panel('options').content);
						var src = content.attr('src');
						$('#tt').tabs('update', {
							tab : currTab,
							options : {
								content : content
							}
						});
					}
				} ]
			});
		}
	}
	function updatepassword() {
		$("#userName").textbox("setValue", '${user.userName}')
		$("#id").val('${user.id}')
		$("#win").window("open");
	}
	function closeFun() {
		$("#win").window("close");
	}

	function submitFun() {
		var newpass = $("#newpassword").passwordbox("getValue");
		var renewpass = $("#renewpassword").passwordbox("getValue");
		$('#Form').form({
			url : "<%=request.getContextPath()%>/user/updatepassword.do",
			onSubmit : function() {
				var temp = $(this).form('validate');
				if (!temp) {
					$.messager.alert('提示', '请填写完整!', "error");
				}
				if (newpass != renewpass) {
					$.messager.alert('提示', '两次密码不一致!', "error");
					temp = false;
				}
				return temp;
			},
			queryParams : {
				newpassword : newpass,
			},
			success : function(data) {
				if (data == "ok") {
					$.messager.alert("提示", "修改成功", "info");
					closeFun();
				} else if (data == "err") {
					$.messager.alert("提示", "原密码错误", "error");
				}
			}
		});
		// submit the form    
		$('#Form').submit();
	}

	function explain() {
		$('#explain').window("open");
	}
</script>
</head>
<body class="easyui-layout" id="layout">
	<div data-options="region:'north'" style="height: 80px; background-color: #E0ECFF">
		<table style="width: 100%">
			<tr>
				<td width="50%">
					<img src="<%=request.getContextPath()%>/images/bglogo.jpg" />
				</td>
				<td valign="bottom" align="right" width="50%">
					<p>
						<a class="main_a" href="#" onclick="javascript:explain();">使用流程 </a>
						&nbsp;&nbsp;
						<strong style="font-size: 15px;">|</strong>
						&nbsp;&nbsp;
						<a class="main_a" href="#" onclick="javascript:updatepassword();">密码修改 </a>
						&nbsp;&nbsp;
						<strong style="font-size: 15px;">|</strong>
						&nbsp;&nbsp;
						<a class="main_a" style="margin-right: 20px;" href="<%=request.getContextPath()%>/user/exit.do">退出</a>
					</p>
					<span class="main_a" style="margin-right: 50px;">
						&nbsp;&nbsp;
						<strong>欢迎：</strong>
						[ ${user.trueName} ]
					</span>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'west',title:'菜单',split:true" style="width: 200px;">

		<div id="usermenu"></div>


		<!-- 		<div title="学生信息" data-options="iconCls:'icon-yxgl'" style="padding: 10px">学生信息</div> -->
		<!-- 			<div title="学生信息" data-options="iconCls:'icon-yxgl'" style="padding: 10px"> -->
		<!-- 				<a href="javascript:add('个人考勤信息','stuCheckin.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">个人考勤信息</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('作业完成情况','homeworkSta.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">作业完成情况</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('个人学习进度','echarts.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">个人学习进度</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('请假申请','stuLeave.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">请假申请</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('学生反馈表','stuQuestion.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">学生反馈表</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('个人项目','stuProject.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">个人项目</a> -->
		<!-- 				<br> -->
		<!-- 			</div> -->
		<!-- 			<div title="教学管理" data-options="iconCls:'icon-yxgl'" style="padding: 10px"> -->
		<!-- 				<a href="javascript:add('班级管理','adminClass.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">班级管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('考勤管理','adminCheckin.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">考勤管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('作业管理','adminHomework.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">作业管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('请假管理','adminLeave.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">请假管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('学生项目管理','adminProject.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">项目管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('问卷管理','adminQuestion.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">问卷管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('问卷试题管理','adminUpdateQues.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">问卷试题管理</a> -->
		<!-- 			</div> -->
		<!-- 			<div title="数据统计" data-options="iconCls:'icon-yxgl'" style="padding: 10px"> -->
		<!-- 				<a href="javascript:add('月度考核统计','adminAssessment.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">月度考核统计</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('作业情况统计','adminHomStaStatistics.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">作业情况统计</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('学生考勤统计','adminCheckinStatistics.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">学生考勤统计</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('问卷信息统计','adminQuestionStatistics.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">问卷信息统计</a> -->
		<!-- 				<br> -->
		<!-- 			</div> -->
		<!-- 			<div title="系统功能" data-options="selected:true,iconCls:'icon-yxgl'" style="padding: 10px"> -->
		<!-- 				<a href="javascript:add('用户管理','adminUser.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">用户管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('角色管理','adminRole.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">角色管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('菜单管理','adminMenu.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">菜单管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('权限管理','adminRolePerm.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">权限管理</a> -->
		<!-- 				<br> -->
		<!-- 				<a href="javascript:add('基础数据','adminDictionary.jsp')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">基础数据</a> -->
		<!-- 				<br> -->
		<!-- 				<br> -->
		<!-- 			</div> -->
	</div>
	<div data-options="region:'center'" style="background: #eee;">
		<div id="tt" class="easyui-tabs" data-options="fit : true,border : false">
			<div title="首页" style="display: none;">
				<div align="center" style="padding-top: 100px">
					<font color="red" size="10">欢迎使用</font>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south'" style="height: 20px; padding: 0px;" align="center">
		版权所有
		<a href="#">http://www.***.com</a>
		(2017-2018)
	</div>
	<div id="win">
		<form id="Form" method="post">
			<input name="id" id="id" hidden="true">
			<table style="margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;" cellpadding="5">
				<tr>
					<td>
						<label>用户账号:</label>
					</td>
					<td>
						<input id="userName" class="easyui-textbox" data-options="required:true,editable:false," style="width: 150px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>原密码:</label>
					</td>
					<td>
						<input id="password" name="password" class="easyui-passwordbox" data-options="required:true" style="width: 150px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>新密码:</label>
					</td>
					<td>
						<input id="newpassword" class="easyui-passwordbox" data-options="required:true" style="width: 150px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
						<label>确认密码:</label>
					</td>
					<td>
						<input id="renewpassword" class="easyui-passwordbox" data-options="required:true" style="width: 150px; height: 30px;">
					</td>
				</tr>
			</table>
			<br>
			<a class="easyui-linkbutton" onclick="submitFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 15%" data-options="plain:true">保存</a>
			<a href="#" class="easyui-linkbutton" onclick="closeFun()" style="background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;" data-options="plain:true">取消</a>
		</form>
	</div>
	<div id="explain">
		1.每日流程 学生考勤查阅，修正。（由班长录入） 按每日教学内容真实填写教学进度。
		<br>
		2.每周流程 (1)每周一，必须点击学生反馈，将自动创建本周每个学生反馈记录，由学生填写。
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (2)每周五之前，如实填写完成教学回馈。作为周会参阅信息。
		<br>
		3.日常流程 (1)使用作业管理，填写日常作业信息。通过点击作业详情，将自动创建每个学生对该作业的记录。 可由学生自己填写作业完成状况，由讲师抽查修改，修改后点击锁定后，学生将不能修改。
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (2)日常多与学生交流，实时填写学生交流记录。
		<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (3)学生提出请假申请时，将先由讲师同意请假申请后，再由教务同意申请，则请假有效。
		<br>
		4.就业前流程 讲师按了解信息，填写修改学生就业准备信息
		<br>
		5.学生信息查看 可通过教学管理-学生状况，了解学生信息
	</div>
</body>
</html>