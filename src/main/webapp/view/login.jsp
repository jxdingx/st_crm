<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户关系管理系统登录</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<style type=text/css>
body {
	text-align: center;
	padding-bottom: 0px;
	background-color: #ddeef2;
	margin: 0px;
	padding-left: 0px;
	padding-right: 0px;
	padding-top: 0px
}

a:link {
	color: #000000;
	text-decoration: none
}

a:visited {
	color: #000000;
	text-decoration: none
}

a:hover {
	color: #ff0000;
	text-decoration: underline
}

a:active {
	text-decoration: none
}

.input {
	border-bottom: #ccc 1px solid;
	border-left: #ccc 1px solid;
	line-height: 20px;
	width: 182px;
	height: 20px;
	border-top: #ccc 1px solid;
	border-right: #ccc 1px solid
}

.input1 {
	border-bottom: #ccc 1px solid;
	border-left: #ccc 1px solid;
	line-height: 20px;
	width: 120px;
	height: 20px;
	border-top: #ccc 1px solid;
	border-right: #ccc 1px solid;
}
</style>
<script type="text/javascript">
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		var rolename = $("#rolename").val();
		var code = $("#code").val();
		if (username == null || username == "") {
			alert("用户名不能为空！");
			return;
		}
		if (password == null || password == "") {
			alert("密码不能为空！");
			return;
		}
		if (rolename == null || rolename == "") {
			alert("请选择用户类型！");
			return;
		}
		if (code == null || code == "") {
			alert("请输入验证码！");
			return;
		}
		$.ajax({
			url : "<%=request.getContextPath()%>/user/login.do",
			type : "POST",
			data : $("#LoginForm").serialize(),
			dataType : "text",
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
			success : function(data) {
				if (data == "ok") {
					window.location.href = "<%=request.getContextPath()%>/view/main.jsp";
				} else if (data == "errcode") {
					$("#err_mess").text("验证码错误!");
				} else if (data == "fail") {
					$("#err_mess").text("该用户不存在!");
				}
			}
		});

	}
	function reset() {
		document.getElementById("LoginForm").reset();
	}
</script>
</head>
<body>

	<table style="margin: auto; width: 100%; height: 100%" border=0 cellspacing=0 cellpadding=0>
		<tbody>
			<tr>
				<td height=150>&nbsp;</td>
			</tr>
			<tr style="height: 254px">
				<td>
					<div style="margin: 0px auto; width: 936px">
						<img style="display: block" src="<%=request.getContextPath()%>/images/body_03.jpg">
					</div>
					<div style="background-color: #278296">
						<div style="margin: 0px auto; width: 936px">
							<div style="background: url(<%=request.getContextPath()%>/images/body_05.jpg) no-repeat; height: 155px">
								<div style="text-align: left; width: 265px; float: right; height: 125px; _height: 95px">
									<form id="LoginForm">
										<table border=0 cellspacing=0 cellpadding=0 width="100%">
											<tbody>
												<tr>
													<td style="height: 45px">
														<input type="text" class='input' name="userName" id="username">
													</td>
												</tr>
												<tr>
													<td>
														<input type="password" class='input' name="password" id="password" />
													</td>
												</tr>
												<tr>
												<tr>

													<td>
														<select id="rolename" name="loginType" class="input" style="margin-top: 15px; height: 24px">
															<option value="">请选择用户类型...</option>
															<option value="1">学生</option>
															<option value="2">员工</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>
														<img style="margin-top: 10px; vertical-align: middle;" src="<%=request.getContextPath()%>/ImageServlet" onclick="this.src='<%=request.getContextPath()%>/ImageServlet?'+Math.random()" />
														<input type="text" name="code" id="code" style="vertical-align: middle; width: 108px; height: 17px; margin-top: 10px;" placeholder="验证码">
														<span id="err_mess" style="color: red; font-size: 12px;"></span>
													</td>
												</tr>
											</tbody>
										</table>
									</form>
								</div>
								<div style="height: 1px; clear: both"></div>
							</div>
						</div>
					</div>
					<div style="margin: 0px auto; width: 936px; height: 80px;">
						<div style="background: url(<%=request.getContextPath()%>/images/body_07.jpg) no-repeat; height: 90px;margin-left: 1px;">
							<div style="width: 380px; float: right; clear: both">
								<table border='0' cellSpacing='0' cellPadding='0' width='300'>
									<tbody>
										<tr>
											<td width="100" align='right'>
												<input style="border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px" src="<%=request.getContextPath()%>/images/btn1.jpg" type='image'
													onclick="login()">
											</td>
											<td width="100" align='middle'>
												<input style="border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px" src="<%=request.getContextPath()%>/images/btn2.jpg" type='image'
													onclick="reset()">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<img src="<%=request.getContextPath()%>/images/body_06.jpg" style="margin-left: 4px;">
						</div>
					</div>
				</td>
			</tr>
			<tr style="height: 30%">
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
</body>
</html>