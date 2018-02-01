<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/js/echarts.js'></script>
<title>Insert title here</title>
</head>
<body>
	<div style="background: white; margin-top: 0px; width: 100%; height: 500px;">
		<div id="homeworksta" style="width: 1300px; height: 400px;"></div>
	</div>
	<script type="text/javascript">
		//初始化图表标签
		var myChart = echarts.init(document.getElementById('homeworksta'));
		myChart.setOption({
			//定义一个标题
			title : {
				text : '问卷回答统计'
			},
			legend : {
				data : [ 'A 选项', 'B 选项', 'C 选项', 'D 选项' ]
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} 人数"
			},
			toolbox : {
				show : true,
				feature : {
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			//X轴设置
			xAxis : {
				data : []
			// 				data : [ '60分', '70分', '80分', '90分', '100分' ]
			},
			yAxis : {},
			series : [ {
				name : '人数',
				type : 'bar',
				data : []
			} ]

		});
		myChart.showLoading();
		//调用方法：var 变量名 = getParameter("要获取的参数名", URL地址)   
		var classId = getParameter("classId").split("=")[1];
		var questionId = getParameter("questionId").split("=")[1];
		$.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/statistics/getquestionsta.do", //请求发送到TestServlet处
			data : {
				classId : classId,
				questionId : questionId,
			},
			dataType : "json", //返回数据形式为json
			success : function(data) {
				// 				alert(JSON.stringify(data));
				if (data) {
					myChart.hideLoading(); //隐藏加载动画
					myChart.setOption({ //加载数据图表
						xAxis : {
							data : data.X
						},
						series : [ {
							// 根据名字对应到相应的系列
							name : 'A 选项',
							type : 'bar',
							data : data.A
						}, {
							// 根据名字对应到相应的系列
							name : 'B 选项',
							type : 'bar',
							data : data.B
						}, {
							// 根据名字对应到相应的系列
							name : 'C 选项',
							type : 'bar',
							data : data.C
						}, {
							// 根据名字对应到相应的系列
							name : 'D 选项',
							type : 'bar',
							data : data.D
						} ]
					});
				}
			}
		});

		function getParameter(paraStr) {
			var url = location.href;
			var result = "";
			//获取URL中全部参数列表数据   
			var str = "&" + url.split("?")[1];
			var paraName = paraStr + "=";
			//判断要获取的参数是否存在   
			if (str.indexOf("&" + paraName) != -1) {
				//如果要获取的参数到结尾是否还包含“&”   
				if (str.substring(str.indexOf(paraName), str.length).indexOf(
						"&") != -1) {
					//得到要获取的参数到结尾的字符串   
					var TmpStr = str.substring(str.indexOf(paraName),
							str.length);
					//截取从参数开始到最近的“&”出现位置间的字符   
					result = TmpStr.substr(TmpStr.indexOf(paraName), TmpStr
							.indexOf("&")
							- TmpStr.indexOf(paraName));
				} else {
					result = str.substring(str.indexOf(paraName), str.length);
				}
			} else {
				result = "无此参数";
			}
			return (result.replace("&", ""));
		}
	</script>
</body>
</html>