package com.st.model;

import com.core.model.BaseModel;

public class QuestionModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer creatQuesId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCreatQuesId() {
		return creatQuesId;
	}

	public void setCreatQuesId(Integer creatQuesId) {
		this.creatQuesId = creatQuesId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
