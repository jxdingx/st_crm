/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.7.v20160115
 * Generated at: 2018-01-30 06:46:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminCheckin_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/css/crm.css\" />\r\n");
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
      out.write("<title></title>\r\n");
      out.write("</head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\teasyuiinit();\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
      out.write("\tfunction easyuiinit() {\r\n");
      out.write("\t\t$('#dg').datagrid({\r\n");
      out.write("\t\t\ttoolbar : '#tb',\r\n");
      out.write("\t\t\tfit : true,\r\n");
      out.write("\t\t\tcheckOnSelect : false,\r\n");
      out.write("\t\t\tfitColumns : true,\r\n");
      out.write("\t\t\tstriped : true,\r\n");
      out.write("\t\t\trownumbers:true,\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('#win').window({\r\n");
      out.write("\t\t\ttitle : '请假申请表',\r\n");
      out.write("\t\t\twidth : 650,\r\n");
      out.write("\t\t\theight : 370,\r\n");
      out.write("\t\t\tclosed : true,//初始时是关闭状态\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tmodal : true,\r\n");
      out.write("\t\t\tdoSize : true,\r\n");
      out.write("\t\t\tborder : 'thin',\r\n");
      out.write("\t\t\tyIndex : 300,\r\n");
      out.write("\t\t\tonClose:function(){\r\n");
      out.write("\t\t\t$('#Form').form(\"clear\");\r\n");
      out.write("\t\t\t$(\"#id\").textbox('textbox').css(\"color\", \"black\");\r\n");
      out.write("\t\t\t$(\"#trueName\").textbox('textbox').css(\"color\", \"black\");\r\n");
      out.write("\t\t\t$(\".checkbox\").each(function() {\r\n");
      out.write("\t\t\t$(this).prop(\"hidden\", true);\r\n");
      out.write("\t\t\t$(this).prev().prev().textbox(\"enable\");\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t//模态\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('#leavewin').window({\r\n");
      out.write("\t\t\ttitle : '请假申请表',\r\n");
      out.write("\t\t\twidth : 700,\r\n");
      out.write("\t\t\theight : 250,\r\n");
      out.write("\t\t\tclosed : true,//初始时是关闭状态\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tmodal : true,\r\n");
      out.write("\t\t\tdoSize : true,\r\n");
      out.write("\t\t\tborder : 'thin',\r\n");
      out.write("\t\t\tyIndex : 300,\r\n");
      out.write("\t\t//模态\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$('#class').combobox({\r\n");
      out.write("\t\t\teditable : false,\r\n");
      out.write("\t\t\turl : '");
      out.print(request.getContextPath());
      out.write("/rolePrem/getclass.do',\r\n");
      out.write("\t\t\tvalueField : 'id',\r\n");
      out.write("\t\t\ttextField : 'className',\r\n");
      out.write("\t\t\tonLoadSuccess : function(data) {\r\n");
      out.write("\t\t\t\tif (data) {\r\n");
      out.write("\t\t\t\t\t$('#class').combobox('setValue', data[0].id);\r\n");
      out.write("\t\t             selectallstu();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar checktime;\r\n");
      out.write("\tvar classId;\r\n");
      out.write("\tvar checkuserId = \"\";\r\n");
      out.write("\tvar alluserId = \"\";\r\n");
      out.write("\r\n");
      out.write("\tfunction selectallstu() {\r\n");
      out.write("\t\tchecktime = $(\"#checktime\").datebox(\"getValue\");\r\n");
      out.write("\t\tclassId=$(\"#class\").combobox(\"getValue\");\r\n");
      out.write("\t\t$('#dg').datagrid({\r\n");
      out.write("\t\t\turl : \"");
      out.print(request.getContextPath());
      out.write("/checkin/selectallStu.do\",\r\n");
      out.write("\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\tclassId : classId,\r\n");
      out.write("\t\t\t\ttime : checktime\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tonLoadSuccess:function(data){\r\n");
      out.write("\t\t     alluserId = \"\";\r\n");
      out.write("\t\t    var rows = data.rows;\r\n");
      out.write("\t\t    var length = rows.length;\r\n");
      out.write("\t\t    for (var i = 0; i < length; i++) {\r\n");
      out.write("\t\t\t alluserId += rows[i].id + \",\";\r\n");
      out.write("\t\t     }\r\n");
      out.write("\t\t    getclasssta(classId);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction checkhidden(content) {\r\n");
      out.write("\t\tvar content = $(\"#\" + content);\r\n");
      out.write("\t\tcontent.prop(\"hidden\", content.prop(\"hidden\") ? false : true);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction updateit(idd, id, name) {\r\n");
      out.write("\t\t$('#Form').form(\"clear\");\r\n");
      out.write("\t\t$('#win').window(\"open\");\r\n");
      out.write("\t\tif (idd != 0) {\r\n");
      out.write("\t\t\t$('#Form').form('load', '");
      out.print(request.getContextPath());
      out.write("/checkin/getcheckin.do?id=' + idd);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#id\").textbox(\"setValue\", id);\r\n");
      out.write("\t\t$(\"#trueName\").textbox(\"setValue\", name);\r\n");
      out.write("\t\tcheckuserId = \"\";\r\n");
      out.write("\t\tcheckuserId = id + \",\"\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction updateselect() {\r\n");
      out.write("\t\tvar rows = $(\"#dg\").datagrid(\"getChecked\");\r\n");
      out.write("\t\tif (rows == '') {\r\n");
      out.write("\t\t\t$.messager.alert(\"提示\", \"请选择需要修改的数据！\", \"warning\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar length = rows.length;\r\n");
      out.write("\t\tcheckuserId = \"\";\r\n");
      out.write("\t\tfor (var i = 0; i < length; i++) {\r\n");
      out.write("\t\t\tcheckuserId += rows[i].id + \",\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$('#win').window(\"open\");\r\n");
      out.write("\t\t$(\"#id\").textbox(\"setValue\", \"选中的记录\");\r\n");
      out.write("\t\t$(\"#id\").textbox('textbox').css(\"color\", \"red\");\r\n");
      out.write("\t\t$(\"#trueName\").textbox(\"setValue\", \"选中的记录\");\r\n");
      out.write("\t\t$(\"#trueName\").textbox('textbox').css(\"color\", \"red\");\r\n");
      out.write("\t\t$(\".checkbox\").each(function() {\r\n");
      out.write("\t\t\t$(this).prop(\"hidden\", false);\r\n");
      out.write("\t\t\t$(this).prev().prev().textbox(\"disable\");\r\n");
      out.write("\t\t\t$(\".checkbox\").click(function() {\r\n");
      out.write("\t\t\t\tif ($(this).is(':checked')) {\r\n");
      out.write("\t\t\t\t\t$(this).prev().prev().textbox(\"enable\");\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t$(this).prev().prev().textbox(\"disable\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t})\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction closeFun() {\r\n");
      out.write("\t\t$('#win').window(\"close\");\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction submitFun() {\r\n");
      out.write("\t\t$('#Form').form({\r\n");
      out.write("\t\t\turl : \"");
      out.print(request.getContextPath());
      out.write("/checkin/updateStu.do\",\r\n");
      out.write("\t\t\tonSubmit : function() {\r\n");
      out.write("\t\t\t\tvar temp = $(this).form('validate');\r\n");
      out.write("\t\t\t\tif (!temp) {\r\n");
      out.write("\t\t\t\t\t$.messager.alert('提示', '请填写完整!', \"error\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn temp;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\tchecktime : checktime,\r\n");
      out.write("\t\t\t\tcheckuserId : checkuserId,\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tif (data) {\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"提示\", \"修改成功\", \"info\");\r\n");
      out.write("\t\t\t\t\tcloseFun();\r\n");
      out.write("\t\t\t\t\t$('#dg').datagrid('reload');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t// submit the form    \r\n");
      out.write("\t\t$('#Form').submit();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction cleanallstu() {\r\n");
      out.write("\t\tvar r = $.messager.confirm('确认', '确认清除今天所有学生的考勤?', function(r) {\r\n");
      out.write("\t\t\tif (r == true) {\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl : '");
      out.print(request.getContextPath());
      out.write("/checkin/cleanallstu.do',\r\n");
      out.write("\t\t\t\t\ttype : 'POST',\r\n");
      out.write("\t\t\t\t\tdata : {\r\n");
      out.write("\t\t\t\t\t\tchecktime : checktime,\r\n");
      out.write("\t\t\t\t\t\tcleanuserid : alluserId,\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tdataType : 'text', //返回的数据格式：json/xml/html/script/jsonp/text\r\n");
      out.write("\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\tif (data) {\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert(\"提示\", \"清除成功\", \"info\");\r\n");
      out.write("\t\t\t\t\t\t\tcloseFun();\r\n");
      out.write("\t\t\t\t\t\t\t$('#dg').datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction leaveallstu() {\r\n");
      out.write("\t\t$('#leavewin').window(\"open\");\r\n");
      out.write("\t\t$('#leavedg').datagrid({\r\n");
      out.write("\t\t\tqueryParams : {\r\n");
      out.write("\t\t\t\ttime : checktime,\r\n");
      out.write("\t\t\t\tclassId : classId,\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\turl : \"");
      out.print(request.getContextPath());
      out.write("/leave/selectallStu.do\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tvar ff = function(value, row, index) {\r\n");
      out.write("\t\tvar idd = 0;\r\n");
      out.write("\t\tvar id = row.id;\r\n");
      out.write("\t\tvar name = row.trueName\r\n");
      out.write("\t\tif (row.checkinModel) {\r\n");
      out.write("\t\t\tidd = row.checkinModel.id;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn \"<a onclick='javascript:updateit(\" + idd + \",\" + id + \",\\\"\" + name\r\n");
      out.write("\t\t\t\t+ \"\\\"\"+\");'>修改</a>\";\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tvar fff = function(value, row, index) {\r\n");
      out.write("\t\tif (row.checkinModel) {\r\n");
      out.write("\t\t\treturn row.checkinModel.time;\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\treturn checktime;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction getclasssta(classId) {\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : '");
      out.print(request.getContextPath());
      out.write("/class/getclasssta.do',\r\n");
      out.write("\t\t\ttype : 'POST',\r\n");
      out.write("\t\t\tdata : {\r\n");
      out.write("\t\t\t\tid : classId\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tdataType : 'json', //返回的数据格式：json/xml/html/script/jsonp/text\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tsetusersta(data)\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction setusersta(data) {\r\n");
      out.write("\t\t$(\"#searchclassName\").text(data.className);\r\n");
      out.write("\t\t$(\"#searchteacherName\").text(data.teacherName);\r\n");
      out.write("\t\t$(\"#searchstuNum\").text(data.stuNum);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction printit() {\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$.messager.confirm(\"操作提示\", \"您确定要打印吗？\", function (data) {\r\n");
      out.write("            if (data) {\r\n");
      out.write("            \twindow.print();//打印\r\n");
      out.write("            }\r\n");
      out.write("            else {\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"tb\">\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<div style=\"height: 30px; margin-top: 10px;\">\r\n");
      out.write("\t\t\t\t<span id=\"color\" style=\"margin-left: 43%; font-size: 20px; color: rgb(26, 51, 243);\">学生考勤管理</span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<label onclick=\"checkhidden('content1')\">查看内容 ▼</label>\r\n");
      out.write("\t\t\t<div style=\"margin-top: 10px; font-size: 15px; margin-left: 15%\" id=\"content1\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t<div style=\"margin-top: 10px;\">\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label>班级:</label>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label id=\"searchclassName\" style=\"border-bottom: 1px solid black; display: block; width: 100px;\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label style=\"margin-left: 120px;\">讲师:</label>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label id=\"searchteacherName\" style=\"border-bottom: 1px solid black; display: block; width: 100px;\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label style=\"margin-left: 120px;\">班级人数:</label>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<label id=\"searchstuNum\" style=\"border-bottom: 1px solid black; display: block; width: 100px;\"></label>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<label onclick=\"checkhidden('content2')\">查询内容 ▼</label>\r\n");
      out.write("\t\t\t<div style=\"margin-top: 10px; font-size: 15px; margin-left: 13%\" id=\"content2\">\r\n");
      out.write("\t\t\t\t&nbsp; &nbsp;&nbsp;当前班级:\r\n");
      out.write("\t\t\t\t<select id=\"class\" name=\"class\" style=\"width: 100px; height: 30px;\">\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t&nbsp; &nbsp;&nbsp;日期:\r\n");
      out.write("\t\t\t\t<input id=\"checktime\" type=\"text\" class=\"easyui-datebox\" editable=\"false\" value=\"now()\" style=\"width: 150px; height: 30px;\" required=\"required\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"selectallstu()\" style=\"background: #E0ECFF; width: 100px; height: 31px; margin-left: 100px;\" data-options=\"plain:true,iconCls:'icon-search'\">\r\n");
      out.write("\t\t\t\t\t<span class=\"crmbuttonfont\">查询</span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"updateselect()\" style=\"background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;\" data-options=\"plain:true,iconCls:'icon-edit'\">\r\n");
      out.write("\t\t\t\t\t<span class=\"crmbuttonfont\">批量修改</span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"cleanallstu()\" style=\"background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;\" data-options=\"plain:true,iconCls:'icon-tip'\">\r\n");
      out.write("\t\t\t\t\t<span class=\"crmbuttonfont\">清除考勤</span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"leaveallstu()\" style=\"background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;\" data-options=\"plain:true,iconCls:'icon-man'\">\r\n");
      out.write("\t\t\t\t\t<span class=\"crmbuttonfont\">请假记录</span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t<a href=\"#\"  class=\"easyui-linkbutton\" onclick=\"printit()\" style=\"background: #E0ECFF; width: 100px; height: 31px; margin-left: 12px;\" data-options=\"plain:true,iconCls:'icon-man'\">\r\n");
      out.write("\t\t\t\t\t<span class=\"crmbuttonfont\">打印</span>\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<input type=\"text\" id=\"time\" hidden=\"true\">\r\n");
      out.write("\t<table id=\"dg\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'cb',checkbox:'true',align:'center'\"></th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'id'\" hidden=\"true\"></th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'time',width:80,align:'center',formatter: fff,align:'center'\">日期</th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'trueName',width:100,align:'center'\">学生姓名</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'onecheck',width:80,align:'center',formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.onecheckName;\r\n");
      out.write("\t\t\t\t} else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\">早晨</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'twocheck',align:'center',width:80,formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.twocheckName;\r\n");
      out.write("\t\t\t\t} else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\">午饭前</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'threecheck',align:'center',width:80,formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.threecheckName;\r\n");
      out.write("\t\t\t\t} else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\">午饭后</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'fourcheck',align:'center',width:80,formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.fourcheckName;\r\n");
      out.write("\t\t\t\t} else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\">晚饭前</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'fivecheck',align:'center',width:80,formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.fivecheckName;\r\n");
      out.write("\t\t\t\t} else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\">晚饭后</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'sixcheck',align:'center',width:80,formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel !=null){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.sixcheckName;\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t}\">晚自习</th>\r\n");
      out.write("\t\t\t\t<th\r\n");
      out.write("\t\t\t\t\tdata-options=\"field:'remark',align:'center',width:80,formatter: function(value,row,index){\r\n");
      out.write("\t\t\t\t\tif (row.checkinModel){\r\n");
      out.write("\t\t\t\t\treturn row.checkinModel.remark;\r\n");
      out.write("\t\t\t\t} else{\r\n");
      out.write("\t\t\t\treturn '-';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\">备注</th>\r\n");
      out.write("\t\t\t\t<th data-options=\"field:'ids',align:'center',width:80,formatter: ff\">操作</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div id=\"win\">\r\n");
      out.write("\t\t<form id=\"Form\" method=\"post\">\r\n");
      out.write("\t\t\t<table style=\"margin: 0px 0px 0px 40px; border-collapse: separate; border-spacing: 10px;\" cellpadding=\"5\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>学生编号:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"id\" class=\"easyui-textbox\" data-options=\"required:true,editable:false\" style=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label style=\"margin-left: 60px;\">学生姓名:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=\"trueName\" class=\"easyui-textbox\" data-options=\"required:true,editable:false\" style=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>早晨:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"onecheck\" data-options=\"required:true,editable:false,valueField:'code',textField:'name',url:'");
      out.print(request.getContextPath());
      out.write("/checkin/getchecksta.do'\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label style=\"margin-left: 60px;\">午饭前:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"twocheck\" data-options=\"required:true,editable:false,valueField:'code',textField:'name',url:'");
      out.print(request.getContextPath());
      out.write("/checkin/getchecksta.do'\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>午饭后:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"threecheck\" data-options=\"required:true,editable:false,valueField:'code',textField:'name',url:'");
      out.print(request.getContextPath());
      out.write("/checkin/getchecksta.do'\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label style=\"margin-left: 60px;\">晚饭前:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"fourcheck\" data-options=\"required:true,editable:false,valueField:'code',textField:'name',url:'");
      out.print(request.getContextPath());
      out.write("/checkin/getchecksta.do'\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label>晚饭后:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"fivecheck\" data-options=\"required:true,editable:false,valueField:'code',textField:'name',url:'");
      out.print(request.getContextPath());
      out.write("/checkin/getchecksta.do'\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<label style=\"margin-left: 60px;\">晚自习:</label>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"sixcheck\" data-options=\"required:true,editable:false,valueField:'code',textField:'name',url:'");
      out.print(request.getContextPath());
      out.write("/checkin/getchecksta.do'\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 130px; height: 30px;\">\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\t\t\t\t<label style=\"margin-left: 60px;\">备注:</label>\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t<input name=\"remark\" class=\"easyui-textbox\" data-options=\"multiline:true\" style=\"width: 410px; height: 60px;\">\r\n");
      out.write("\t\t\t\t<input type=\"checkbox\" class=\"checkbox\" hidden=\"true\">\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" onclick=\"submitFun()\" style=\"background: #F4F4F4; width: 100px; height: 34px; margin-left: 30%\" data-options=\"plain:true\">保存</a>\r\n");
      out.write("\t\t\t<a href=\"#\" class=\"easyui-linkbutton\" onclick=\"closeFun()\" style=\"background: #F4F4F4; width: 100px; height: 34px; margin-left: 40px;\" data-options=\"plain:true\">取消</a>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"leavewin\">\r\n");
      out.write("\t\t<table id=\"leavedg\">\r\n");
      out.write("\t\t\t<thead>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'userName',width:80\">学生</th>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'reason',width:130\">原因</th>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'time',width:150\">申请时间</th>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'startTime',width:150\">开始时间</th>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'endTime',width:150\">截止时间</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</thead>\r\n");
      out.write("\t\t</table>\r\n");
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
