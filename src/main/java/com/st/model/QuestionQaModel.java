package com.st.model;

import com.core.model.BaseModel;

public class QuestionQaModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String optionsA;
	private String optionsB;
	private String optionsC;
	private String optionsD;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptionsA() {
		return optionsA;
	}

	public void setOptionsA(String optionsA) {
		this.optionsA = optionsA;
	}

	public String getOptionsB() {
		return optionsB;
	}

	public void setOptionsB(String optionsB) {
		this.optionsB = optionsB;
	}

	public String getOptionsC() {
		return optionsC;
	}

	public void setOptionsC(String optionsC) {
		this.optionsC = optionsC;
	}

	public String getOptionsD() {
		return optionsD;
	}

	public void setOptionsD(String optionsD) {
		this.optionsD = optionsD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
