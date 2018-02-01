package com.st.model;

import com.core.model.BaseModel;

public class CreateQuesRelModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer creatQuesId;
	private String questionQaId;

	public Integer getCreatQuesId() {
		return creatQuesId;
	}

	public void setCreatQuesId(Integer creatQuesId) {
		this.creatQuesId = creatQuesId;
	}

	public String getQuestionQaId() {
		return questionQaId;
	}

	public void setQuestionQaId(String questionQaId) {
		this.questionQaId = questionQaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
