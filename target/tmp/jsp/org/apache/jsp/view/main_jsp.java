/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.7.v20160115
 * Generated at: 2018-01-30 06:43:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/themes/default/easyui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/themes/icon.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<title>客户关系管理系统主页</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".main_a {\r\n");
      out.write("\tfont-size: 16px;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\tvar userId = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("';\r\n");
      out.write("\t\t// \t\talert(userId);\r\n");
      out.write("\t\tif (!userId) {\r\n");
      out.write("\t\t\twindow.location.href = \"");
      out.print(request.getContextPath());
      out.write("/view/login.jsp\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tgetusermenu();\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\tfunction getusermenu() {\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : '");
      out.print(request.getContextPath());
      out.write("/user/getusermenu.do',\r\n");
      out.write("\t\t\ttype : 'POST',\r\n");
      out.write("\t\t\tdataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tmenu(data);\r\n");
      out.write("\t\t\t\tsuccess();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction menu(data) {\r\n");
      out.write("\t\t// \t\talert(JSON.stringify(data.two));\r\n");
      out.write("\t\tif (data) {\r\n");
      out.write("\t\t\tvar one = data.one;\r\n");
      out.write("\t\t\tvar onelength = one.length;\r\n");
      out.write("\t\t\tvar two = data.two;\r\n");
      out.write("\t\t\tvar twolength = two.length;\r\n");
      out.write("\t\t\tvar str = '<div class=\"easyui-accordion\" data-options=\"border:false,height:350\">';\r\n");
      out.write("\t\t\tfor (var i = 0; i < onelength; i++) {\r\n");
      out.write("\t\t\t\tvar str1 = '<div title=\"'+one[i].menuName+'\" data-options=\"iconCls:\\'icon-yxgl\\'\"  style=\"padding: 10px\">'\r\n");
      out.write("\t\t\t\tvar str2 = \"\";\r\n");
      out.write("\t\t\t\tfor (var j = 0; j < twolength; j++) {\r\n");
      out.write("\t\t\t\t\tif (two[j].menuParentCode == one[i].menuCode) {\r\n");
      out.write("\t\t\t\t\t\tstr2 += '<a href=\"javascript:add(\\''\r\n");
      out.write("\t\t\t\t\t\t\t\t+ two[j].menuName\r\n");
      out.write("\t\t\t\t\t\t\t\t+ '\\','\r\n");
      out.write("\t\t\t\t\t\t\t\t+ '\\''\r\n");
      out.write("\t\t\t\t\t\t\t\t+ two[j].menuUrl\r\n");
      out.write("\t\t\t\t\t\t\t\t+ '\\')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:\\'icon-ok\\'\">'\r\n");
      out.write("\t\t\t\t\t\t\t\t+ two[j].menuName + '</a><br>'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar str3 = \"</div>\"\r\n");
      out.write("\t\t\t\tstr += str1 + str2 + str3;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"#usermenu\").html(str + \"</div>\");\r\n");
      out.write("\t\t\t$.parser.parse($(\"#usermenu\"));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction success() {\r\n");
      out.write("\t\t$('#win').window({\r\n");
      out.write("\t\t\ttitle : '密码修改',\r\n");
      out.write("\t\t\twidth : 350,\r\n");
      out.write("\t\t\theight : 300,\r\n");
      out.write("\t\t\tclosed : true,//初始时是关闭状态\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tmodal : true,\r\n");
      out.write("\t\t\tdoSize : true,\r\n");
      out.write("\t\t\tborder : 'thin',\r\n");
      out.write("\t\t\tyIndex : 300,\r\n");
      out.write("\t\t\tonClose : function() {\r\n");
      out.write("\t\t\t\t$('#Form').form(\"clear\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t//模态\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('#explain').window({\r\n");
      out.write("\t\t\ttitle : '密码修改',\r\n");
      out.write("\t\t\twidth : 400,\r\n");
      out.write("\t\t\theight : 350,\r\n");
      out.write("\t\t\tclosed : true,//初始时是关闭状态\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tmodal : true,\r\n");
      out.write("\t\t\tdoSize : true,\r\n");
      out.write("\t\t\tborder : 'thin',\r\n");
      out.write("\t\t\tyIndex : 300,\r\n");
      out.write("\t\t//模态\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction add(TitleText, src) {\r\n");
      out.write("\t\tif ($('#tt').tabs(\"exists\", TitleText)) {\r\n");
      out.write("\t\t\t$('#tt').tabs(\"select\", TitleText)\r\n");
      out.write("\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tvar content = \"<iframe scrolling='no' frameborder='no'  style='width: 100%;height: 100%;' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/view/\"\r\n");
      out.write("\t\t\t\t\t+ src + \"'></iframe>\"\r\n");
      out.write("\t\t\t$('#tt').tabs('add', {\r\n");
      out.write("\t\t\t\ttitle : TitleText,\r\n");
      out.write("\t\t\t\tcontent : content,\r\n");
      out.write("\t\t\t\tclosable : true,\r\n");
      out.write("\t\t\t\ttools : [ {\r\n");
      out.write("\t\t\t\t\ticonCls : 'icon-reload',\r\n");
      out.write("\t\t\t\t\thandler : function() {\r\n");
      out.write("\t\t\t\t\t\tvar currTab = $('#tt').tabs('getSelected');\r\n");
      out.write("\t\t\t\t\t\tvar content = $(currTab.panel('options').content);\r\n");
      out.write("\t\t\t\t\t\tvar src = content.attr('src');\r\n");
      out.write("\t\t\t\t\t\t$('#tt').tabs('update', {\r\n");
      out.write("\t\t\t\t\t\t\ttab : currTab,\r\n");
      out.write("\t\t\t\t\t\t\toptions : {\r\n");
      out.write("\t\t\t\t\t\t\t\tcontent : content\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t} ]\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction updatepassword() {\r\n");
      out.write("\t\t$(\"#userName\").textbox(\"setValue\", '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("')\r\n");
      out.write("\t\t$(\"#id\").val('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("')\r\n");
      out.write("\t\t$(\"#win\").window(\"open\");\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction closeFun() {\r\n");
      out.write("\t\t$(\"#win\").window(\"close\");\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction submitFun() {\r\n");
      out.write("\t\tvar newpass = $(\"#newpassword\").passwordbox(\"getValue\");\r\n");
      out.write("\t\tvar renewpass = $(\"#renewpassword\").passwordbox(\"getValue\");\r\n");
      out.write("\t\t$('#Form').form({\r\n");
      out.write("\t\t\turl : \"");
      out.print(request.getContextPath());
      out.write("/user/updatepassword.do\",\r\n");
      out.write("\t\t\tonSubmit : function() {\r\n");
      out.write("\t\t\t\tvar temp = $(this).form('validate');\r\n");
      out.write("\t\t\t\tif (!temp) {\r\n");
      out.write("\t\t\t\t\t$.messager.alert('提示', '请填写完整!', \"error\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif (newpass != renewpass) {\r\n");
      out.write("\t\t\t\t\t$.messager.alert('提示', '两次密码不一致!', \"error\");\r\n");
      out.write("\t\t\t\t\ttemp = false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn temp;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\tnewpassword : newpass,\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tif (data == \"ok\") {\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"修改成功\", \"info\");\r\n");
      out.write("\t\t\t\t\tcloseFun();\r\n");
      out.write("\t\t\t\t} else if (data == \"err\") {\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"原密码错误\", \"error\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t// submit the form    \r\n");
      out.write("\t\t$('#Form').submit();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction explain() {\r\n");
      out.write("\t\t$('#explain').window(\"open\");\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" id=\"layout\">\r\n");
      out.write("\t<div data-options=\"region:'north'\" style=\"height: 80px; background-color: #E0ECFF\">\r\n");
      out.write("\t\t<table style=\"width: 100%\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"50%\">\r\n");
      out.write("\t\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/images/bglogo.jpg\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td valign=\"bottom\" align=\"right\" width=\"50%\">\r\n");
      out.write("\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t<a class=\"main_a\" href=\"#\" onclick=\"javascript:explain();\">使用流程 </a>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<strong style=\"font-size: 15px;\">|</strong>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<a class=\"main_a\" href=\"#\" onclick=\"javascript:updatepassword();\">密码修改 </a>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<strong style=\"font-size: 15px;\">|</strong>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<a class=\"main_a\" style=\"margin-right: 20px;\" href=\"");
      out.print(request.getContextPath());
      out.write("/user/exit.do\">退出</a>\r\n");
      out.write("\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t<span class=\"main_a\" style=\"margin-right: 50px;\">\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<strong>欢迎：</strong>\r\n");
      out.write("\t\t\t\t\t\t[ ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.trueName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" ]\r\n");
      out.write("\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'west',title:'菜单',split:true\" style=\"width: 200px;\">\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"usermenu\"></div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- \t\t<div title=\"学生信息\" data-options=\"iconCls:'icon-yxgl'\" style=\"padding: 10px\">学生信息</div> -->\r\n");
      out.write("\t\t<!-- \t\t\t<div title=\"学生信息\" data-options=\"iconCls:'icon-yxgl'\" style=\"padding: 10px\"> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('个人考勤信息','stuCheckin.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">个人考勤信息</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('作业完成情况','homeworkSta.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">作业完成情况</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('个人学习进度','echarts.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">个人学习进度</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('请假申请','stuLeave.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">请假申请</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('学生反馈表','stuQuestion.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">学生反馈表</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('个人项目','stuProject.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">个人项目</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t</div> -->\r\n");
      out.write("\t\t<!-- \t\t\t<div title=\"教学管理\" data-options=\"iconCls:'icon-yxgl'\" style=\"padding: 10px\"> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('班级管理','adminClass.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">班级管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('考勤管理','adminCheckin.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">考勤管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('作业管理','adminHomework.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">作业管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('请假管理','adminLeave.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">请假管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('学生项目管理','adminProject.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">项目管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('问卷管理','adminQuestion.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">问卷管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('问卷试题管理','adminUpdateQues.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">问卷试题管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t</div> -->\r\n");
      out.write("\t\t<!-- \t\t\t<div title=\"数据统计\" data-options=\"iconCls:'icon-yxgl'\" style=\"padding: 10px\"> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('月度考核统计','adminAssessment.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">月度考核统计</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('作业情况统计','adminHomStaStatistics.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">作业情况统计</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('学生考勤统计','adminCheckinStatistics.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">学生考勤统计</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('问卷信息统计','adminQuestionStatistics.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">问卷信息统计</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t</div> -->\r\n");
      out.write("\t\t<!-- \t\t\t<div title=\"系统功能\" data-options=\"selected:true,iconCls:'icon-yxgl'\" style=\"padding: 10px\"> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('用户管理','adminUser.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">用户管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('角色管理','adminRole.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">角色管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('菜单管理','adminMenu.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">菜单管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('权限管理','adminRolePerm.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">权限管理</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<a href=\"javascript:add('基础数据','adminDictionary.jsp')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'icon-ok'\">基础数据</a> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t\t<br> -->\r\n");
      out.write("\t\t<!-- \t\t\t</div> -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'center'\" style=\"background: #eee;\">\r\n");
      out.write("\t\t<div id=\"tt\" class=\"easyui-tabs\" data-options=\"fit : true,border : false\">\r\n");
      out.write("\t\t\t<div title=\"首页\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t<div align=\"center\" style=\"padding-top: 100px\">\r\n");
      out.write("\t\t\t\t\t<font color=\"red\" size=\"10\">欢迎使用</font>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div data-options=\"region:'south'\" style=\"height: 20px; padding: 0px;\" align=\"center\">\r\n");
      out.write("\t\t版权所有\r\n");
      out.write("\t\t<a href=\"#\">http://www.***.com</a>\r\n");
      out.write("\t\t(2017-2018)\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"win\">\r\n");
      out.write("\t\t<form id=\"Form\" method=\"post\">\r\n");
      out.write("\t\t\t<input name=\"id\" id=\"id\" hidden=\"true\">\r\n");
      out.write("\t\t\t<table style=\"margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;\" cellpadding=\"5\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>用户账号:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"userName\" class=\"easyui-textbox\" data-options=\"required:true,editable:false,\" style=\"width: 150px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>原密码:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"password\" name=\"password\" class=\"easyui-passwordbox\" data-options=\"required:true\" style=\"width: 150px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>新密码:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"newpassword\" class=\"easyui-passwordbox\" data-options=\"required:true\" style=\"width: 150px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>确认密码:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"renewpassword\" class=\"easyui-passwordbox\" data-options=\"required:true\" style=\"width: 150px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" onclick=\"submitFun()\" style=\"background: #F4F4F4; width: 100px; height: 34px; margin-left: 15%\" data-options=\"plain:true\">保存</a>\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"closeFun()\" style=\"background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;\" data-options=\"plain:true\">取消</a>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"explain\">\r\n");
      out.write("\t\t1.每日流程 学生考勤查阅，修正。（由班长录入） 按每日教学内容真实填写教学进度。\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t2.每周流程 (1)每周一，必须点击学生反馈，将自动创建本周每个学生反馈记录，由学生填写。\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (2)每周五之前，如实填写完成教学回馈。作为周会参阅信息。\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t3.日常流程 (1)使用作业管理，填写日常作业信息。通过点击作业详情，将自动创建每个学生对该作业的记录。 可由学生自己填写作业完成状况，由讲师抽查修改，修改后点击锁定后，学生将不能修改。\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (2)日常多与学生交流，实时填写学生交流记录。\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; (3)学生提出请假申请时，将先由讲师同意请假申请后，再由教务同意申请，则请假有效。\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t4.就业前流程 讲师按了解信息，填写修改学生就业准备信息\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t5.学生信息查看 可通过教学管理-学生状况，了解学生信息\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
