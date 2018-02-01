package com.core.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.st.model.ClassModel;

public class Constant {

	protected static final ResourceBundle res1 = ResourceBundle.getBundle("prop");

	public final static String SYS_SESSION_VALIDATECODE = res1.getString("SYS_SESSION_VALIDATECODE");// 验证码

	public final static String STU_LEAVE = res1.getString("STU_LEAVE");
	public final static String STU_CHECK = res1.getString("STU_CHECK");
	public final static String STU_HOME = res1.getString("STU_HOME");
	public final static String STU_HOME_TYPE = res1.getString("STU_HOME_TYPE");
	public final static String HOME_LOCK = res1.getString("HOME_LOCK");
	public final static String STU_PRO = res1.getString("STU_PRO");

	public final static String MENU_TYPE_ONE = res1.getString("MENU_TYPE_ONE");
	public final static String MENU_TYPE_TWO = res1.getString("MENU_TYPE_TWO");

	public final static Map<String, String> LEAVE_MAP = new HashMap<>();

	public final static Map<String, String> CHECK_MAP = new HashMap<>();

	public final static Map<String, String> HOME_GRADE = new HashMap<>();

	public final static Map<String, String> HOME_TYPE = new HashMap<>();

	public final static Map<String, String> LOCK_MAP = new HashMap<>();

	public final static Map<String, String> PRO_MAP = new HashMap<>();

	public final static List<ClassModel> CLA_List = new ArrayList<>();
	// // 管理员标志-0=否,1=是
	// public final static Integer SYS_ADMIN_0 =
	// Integer.parseInt(res1.getString("SYS_ADMIN_0"));
	// public final static Integer SYS_ADMIN_1 =
	// Integer.parseInt(res1.getString("SYS_ADMIN_1"));;
	//
	// // 状态标志-0=禁用,1=可用
	// public final static Integer SYS_STATE_0 =
	// Integer.parseInt(res1.getString("SYS_STATE_0"));
	// public final static Integer SYS_STATE_1 =
	// Integer.parseInt(res1.getString("SYS_STATE_1"));
	//
	// // 删除标志-0=未删,1=已删
	// public final static Integer SYS_DELETED_0 =
	// Integer.parseInt(res1.getString("SYS_DELETED_0"));
	// public final static Integer SYS_DELETED_1 =
	// Integer.parseInt(res1.getString("SYS_DELETED_1"));
	//
	// // 关联类型（1=A用户B菜单）
	// public final static Integer SYS_REL_TYPE1 =
	// Integer.parseInt(res1.getString("SYS_REL_TYPE1"));
	//
	// public final static String SYS_SESSION_USER =
	// res1.getString("SYS_SESSION_USER");
	// public final static String SYS_ACTION_SUCCESS =
	// res1.getString("SYS_ACTION_SUCCESS");
	// public final static String SYS_ACTION_MSG = res1.getString("SYS_ACTION_MSG");
	// public final static String SYS_DEFAULT_PASSWORD =
	// res1.getString("SYS_DEFAULT_PASSWORD");
	//
	// public final static String MENU_ID = res1.getString("MENU_ID"); // 管理员左侧折叠菜单
	//
	// public final static String SERVER_WEB_TITLE =
	// res1.getString("SERVER_WEB_TITLE");
	// public final static String SERVER_USER_ADMIN =
	// res1.getString("SERVER_USER_ADMIN");
	// public final static String SERVER_AUTO = res1.getString("SERVER_AUTO");
	// public final static String SERVER_msUrl = res1.getString("SERVER_msUrl");
	// public final static String SERVER_NAME = res1.getString("SERVER_NAME");
	// public final static String SERVER_easyuiUrl =
	// res1.getString("SERVER_easyuiUrl");
	// public final static String SERVER_TOMCAT = res1.getString("SERVER_TOMCAT");

}