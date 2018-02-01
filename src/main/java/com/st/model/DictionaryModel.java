package com.st.model;

import com.core.model.BaseModel;

public class DictionaryModel extends BaseModel {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private String type;
	private String pcode;

	private String pcodeName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPcodeName() {
		return pcodeName;
	}

	public void setPcodeName(String pcodeName) {
		this.pcodeName = pcodeName;
	}

}
