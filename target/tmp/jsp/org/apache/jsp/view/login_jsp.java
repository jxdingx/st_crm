/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.3.7.v20160115
 * Generated at: 2018-01-30 06:43:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>客户关系管理系统登录</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery.min.js\"></script>\r\n");
      out.write("<style type=text/css>\r\n");
      out.write("body {\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tpadding-bottom: 0px;\r\n");
      out.write("\tbackground-color: #ddeef2;\r\n");
      out.write("\tmargin: 0px;\r\n");
      out.write("\tpadding-left: 0px;\r\n");
      out.write("\tpadding-right: 0px;\r\n");
      out.write("\tpadding-top: 0px\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:link {\r\n");
      out.write("\tcolor: #000000;\r\n");
      out.write("\ttext-decoration: none\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:visited {\r\n");
      out.write("\tcolor: #000000;\r\n");
      out.write("\ttext-decoration: none\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:hover {\r\n");
      out.write("\tcolor: #ff0000;\r\n");
      out.write("\ttext-decoration: underline\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:active {\r\n");
      out.write("\ttext-decoration: none\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".input {\r\n");
      out.write("\tborder-bottom: #ccc 1px solid;\r\n");
      out.write("\tborder-left: #ccc 1px solid;\r\n");
      out.write("\tline-height: 20px;\r\n");
      out.write("\twidth: 182px;\r\n");
      out.write("\theight: 20px;\r\n");
      out.write("\tborder-top: #ccc 1px solid;\r\n");
      out.write("\tborder-right: #ccc 1px solid\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".input1 {\r\n");
      out.write("\tborder-bottom: #ccc 1px solid;\r\n");
      out.write("\tborder-left: #ccc 1px solid;\r\n");
      out.write("\tline-height: 20px;\r\n");
      out.write("\twidth: 120px;\r\n");
      out.write("\theight: 20px;\r\n");
      out.write("\tborder-top: #ccc 1px solid;\r\n");
      out.write("\tborder-right: #ccc 1px solid;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction login() {\r\n");
      out.write("\t\tvar username = $(\"#username\").val();\r\n");
      out.write("\t\tvar password = $(\"#password\").val();\r\n");
      out.write("\t\tvar rolename = $(\"#rolename\").val();\r\n");
      out.write("\t\tvar code = $(\"#code\").val();\r\n");
      out.write("\t\tif (username == null || username == \"\") {\r\n");
      out.write("\t\t\talert(\"用户名不能为空！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (password == null || password == \"\") {\r\n");
      out.write("\t\t\talert(\"密码不能为空！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (rolename == null || rolename == \"\") {\r\n");
      out.write("\t\t\talert(\"请选择用户类型！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (code == null || code == \"\") {\r\n");
      out.write("\t\t\talert(\"请输入验证码！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : \"");
      out.print(request.getContextPath());
      out.write("/user/login.do\",\r\n");
      out.write("\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\tdata : $(\"#LoginForm\").serialize(),\r\n");
      out.write("\t\t\tdataType : \"text\",\r\n");
      out.write("\t\t\tcontentType : 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tif (data == \"ok\") {\r\n");
      out.write("\t\t\t\t\twindow.location.href = \"");
      out.print(request.getContextPath());
      out.write("/view/main.jsp\";\r\n");
      out.write("\t\t\t\t} else if (data == \"errcode\") {\r\n");
      out.write("\t\t\t\t\t$(\"#err_mess\").text(\"验证码错误!\");\r\n");
      out.write("\t\t\t\t} else if (data == \"fail\") {\r\n");
      out.write("\t\t\t\t\t$(\"#err_mess\").text(\"该用户不存在!\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction reset() {\r\n");
      out.write("\t\tdocument.getElementById(\"LoginForm\").reset();\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<table style=\"margin: auto; width: 100%; height: 100%\" border=0 cellspacing=0 cellpadding=0>\r\n");
      out.write("\t\t<tbody>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td height=150>&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr style=\"height: 254px\">\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<div style=\"margin: 0px auto; width: 936px\">\r\n");
      out.write("\t\t\t\t\t\t<img style=\"display: block\" src=\"");
      out.print(request.getContextPath());
      out.write("/images/body_03.jpg\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div style=\"background-color: #278296\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"margin: 0px auto; width: 936px\">\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"background: url(");
      out.print(request.getContextPath());
      out.write("/images/body_05.jpg) no-repeat; height: 155px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div style=\"text-align: left; width: 265px; float: right; height: 125px; _height: 95px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<form id=\"LoginForm\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<table border=0 cellspacing=0 cellpadding=0 width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td style=\"height: 45px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class='input' name=\"userName\" id=\"username\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"password\" class='input' name=\"password\" id=\"password\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<select id=\"rolename\" name=\"loginType\" class=\"input\" style=\"margin-top: 15px; height: 24px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"\">请选择用户类型...</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"1\">学生</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2\">员工</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img style=\"margin-top: 10px; vertical-align: middle;\" src=\"");
      out.print(request.getContextPath());
      out.write("/ImageServlet\" onclick=\"this.src='");
      out.print(request.getContextPath());
      out.write("/ImageServlet?'+Math.random()\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"code\" id=\"code\" style=\"vertical-align: middle; width: 108px; height: 17px; margin-top: 10px;\" placeholder=\"验证码\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span id=\"err_mess\" style=\"color: red; font-size: 12px;\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div style=\"height: 1px; clear: both\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div style=\"margin: 0px auto; width: 936px; height: 80px;\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"background: url(");
      out.print(request.getContextPath());
      out.write("/images/body_07.jpg) no-repeat; height: 90px;margin-left: 1px;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"width: 380px; float: right; clear: both\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<table border='0' cellSpacing='0' cellPadding='0' width='300'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td width=\"100\" align='right'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input style=\"border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px\" src=\"");
      out.print(request.getContextPath());
      out.write("/images/btn1.jpg\" type='image'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tonclick=\"login()\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td width=\"100\" align='middle'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input style=\"border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px\" src=\"");
      out.print(request.getContextPath());
      out.write("/images/btn2.jpg\" type='image'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tonclick=\"reset()\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/images/body_06.jpg\" style=\"margin-left: 4px;\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr style=\"height: 30%\">\r\n");
      out.write("\t\t\t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
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
