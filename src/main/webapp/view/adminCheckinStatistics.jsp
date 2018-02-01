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
		$('#class').combobox({
			editable : false,
			url : '<%=request.getContextPath()%>/rolePrem/getclass.do',
			valueField : 'id',
			textField : 'className',
			onLoadSuccess : function(data) {
				if (data) {
					$('#class').combobox('setValue', data[0].id);
					statistics();
				}
			}
		});
		$.ajax({
					url : '<%=request.getContextPath()%>/checkin/getchecksta.do',
					type : 'POST',
					dataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text
					success : function(data) {
						if (data) {
							var length = data.length;
							var str = '';
							for (var i = 0; i < length; i++) {
								str += '<a href="#" class="easyui-linkbutton" onclick="bgchang('
										+ data[i].code
										+ ')" style="background: #ff9E94; width: 100px; height: 34px; margin-left: 20px;" data-options="plain:true"><input id="'+data[i].code+'" name="check" type="radio" hidden="true"><span class="crmbuttonfont">'
										+ data[i].name + '</span></a>'
							}
							$("#checksta").html(str);
							$.parser.parse($("#checksta"));
							bgchang(data[0].code);
						}
					},
				});
	}
	var checksta = "";
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
		checksta = num;
		statistics()
	}
	function statistics() {
		var classId = $('#class').combobox("getValue");
		var checktime = $('#checktime').datebox("getValue");
		if (!checktime) {
			$.messager.alert("提示", "请选择查看的日期", "info");
			return;
		}
		echarts(classId, checktime, checksta);
		getclasssta(classId);
	}

	function echarts(classId, checktime, checksta) {
		// 初始化图表标签
		$("#echarts").prop(
				"src",
				"<%=request.getContextPath()%>/view/echartsCheckin.jsp?classId=" + classId
						+ "&checktime=" + checktime + "&checksta=" + checksta);
	}
	// 初始化图表标签
	function checkhidden(content) {
		var content = $("#" + content);
		content.prop("hidden", content.prop("hidden") ? false : true);
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
<body style="margin: 0px 0px 0px 0px; background: #F4F4F4;">
	<div>
		<div style="height: 30px; margin-top: 10px;">
			<span id="color" style="margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);">考勤情况统计</span>
		</div>
		<label onclick="checkhidden('content1')" style="font-size: 12px;">查看内容 ▼</label>
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
		<label onclick="checkhidden('content2')" style="font-size: 12px;">查询内容 ▼</label>
		<div style="margin-top: 10px; font-size: 15px; margin-left: 13%" id="content2">
			&nbsp; &nbsp;&nbsp;当前班级:
			<select id="class" style="width: 100px; height: 30px;">
			</select>
			&nbsp; &nbsp;&nbsp;考勤时间:
			<input id="checktime" type="text" class="easyui-datebox" editable="false" value="now()" style="width: 150px; height: 30px;" required="required">
			<a href="#" class="easyui-linkbutton" onclick="statistics()" style="background: #E0ECFF; width: 100px; height: 31px; margin-left: 200px;" data-options="plain:true,iconCls:'icon-tip'">
				<span class="crmbuttonfont">查询</span>
			</a>
			<br>
			<br>
			<div id="checksta"></div>

			<!-- 			<a href="#" class="easyui-linkbutton" onclick="bgchang(2)" style="background: #ff9E94; width: 100px; height: 34px; margin-left: 30px;" data-options="plain:true,group:'select'"> -->
			<!-- 				<input id="2" name="check" type="radio" hidden="true"> -->
			<!-- 				<span class="crmbuttonfont">迟到</span> -->
			<!-- 			</a> -->
		</div>
		<br>
	</div>
	<div style="background: white; margin-top: 0px; width: 100%; height: 500px">
		<iframe id="echarts" scrolling='no' frameborder='no' style='width: 100%; height: 100%;' src=''></iframe>
	</div>
</body>
</html>