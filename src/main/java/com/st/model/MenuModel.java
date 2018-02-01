package com.st.model;

import com.core.model.BaseModel;

public class MenuModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuCode;
	private String menuParentCode;
	private String menuType;
	private String menuName;
	private String menuUrl;
	private String menuParentName;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuParentCode() {
		return menuParentCode;
	}

	public void setMenuParentCode(String menuParentCode) {
		this.menuParentCode = menuParentCode;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMenuParentName() {
		return menuParentName;
	}

	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}
}
