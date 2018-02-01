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
	<div style="background: white; margin-top: 0px; width: 100%; height: 500px; margin-left: 5%">
		<div id="homeworksta" style="width: 1000px; height: 400px;"></div>
	</div>
	<script type="text/javascript">
		//初始化图表标签
		var myChart = echarts.init(document.getElementById('homeworksta'));
		myChart.setOption({
			//定义一个标题
			title : {
				text : '作业评级分布情况',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} 人数"
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'pie', 'funnel' ],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
								max : 1548
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			series : [ {
				name : '人数',
				type : 'pie',
				radius : '70%',//饼图的半径大小  
				center : [ '50%', '60%' ],//饼图的位置  
				type : 'pie',
			} ]
		});
		myChart.showLoading();
		//调用方法：var 变量名 = getParameter("要获取的参数名", URL地址)   
		var classId = getParameter("classId").split("=")[1];
		var homeworkId = getParameter("homeworkId").split("=")[1];
		$.ajax({
			type : "post",
			url : "<%=request.getContextPath()%>/statistics/gethomeworksta.do",
			data : {
				classId : classId,
				homeworkId : homeworkId,
			},
			dataType : "json", //返回数据形式为json
			success : function(data) {
				if (data) {
					var X = [];
					var Y = [];
					var length = data.length;
					for (var i = 0; i < length; i++) {
						X.push(data[i].name); //挨个取出类别并填入类别数组
						Y.push(data[i].value); //挨个取出销量并填入销量数组
					}
					myChart.hideLoading(); //隐藏加载动画
					myChart.setOption({ //加载数据图表
						legend : {
							orient : 'vertical',
							left : 'right',
							top : "100px",
							data : X,
							formatter : function(name) {
								var array = [];
								var len = X.length;
								for (var i = 0; i < len; i++) {
									if (X[i] == name) {
										return name + "  " + Y[i] + " 人";
									}
								}
								return name;
							}
						},
						series : [ {
							// 根据名字对应到相应的系列
							name : '分段人数',
							type : 'pie',
							data : data
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