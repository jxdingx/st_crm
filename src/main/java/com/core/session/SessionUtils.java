package com.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.core.tool.Constant;

/**
 * Cookie 工具类
 */
public final class SessionUtils {

	private static HttpSession getHttpSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	/**
	 * 设置session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttr(HttpServletRequest request, String key, Object value) {
		getHttpSession(request).setAttribute(key, value);
	}

	/**
	 * 获取session的值
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttr(HttpServletRequest request, String key) {
		return getHttpSession(request).getAttribute(key);
	}

	/**
	 * 删除Session值
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAttr(HttpServletRequest request, String key) {
		getHttpSession(request).removeAttribute(key);
	}

	/**
	 * 设置验证码 到session
	 * 
	 * @param request
	 * @param user
	 */
	public static void setValidateCode(HttpServletRequest request, String validateCode) {
		setAttr(request, Constant.SYS_SESSION_VALIDATECODE, validateCode);
	}

	/**
	 * 从session中获取验证码
	 * 
	 * @param request
	 * @return SysUser
	 */
	public static String getValidateCode(HttpServletRequest request) {
		return getAttr(request, Constant.SYS_SESSION_VALIDATECODE).toString();
	}
}