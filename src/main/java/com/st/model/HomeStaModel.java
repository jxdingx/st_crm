package com.st.model;

import com.core.model.BaseModel;

public class HomeStaModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer homeworkId;
	private Integer oneselfScore;
	private String oneselfGrade;
	private Integer teacherScore;
	private String teacherGrade;

	private String oneselfGradeName;

	private String teacherGradeName;

	private String statusName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(Integer homeworkId) {
		this.homeworkId = homeworkId;
	}

	public Integer getOneselfScore() {
		return oneselfScore;
	}

	public void setOneselfScore(Integer oneselfScore) {
		this.oneselfScore = oneselfScore;
	}

	public String getOneselfGrade() {
		return oneselfGrade;
	}

	public void setOneselfGrade(String oneselfGrade) {
		this.oneselfGrade = oneselfGrade;
	}

	public Integer getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(Integer teacherScore) {
		this.teacherScore = teacherScore;
	}

	public String getTeacherGrade() {
		return teacherGrade;
	}

	public void setTeacherGrade(String teacherGrade) {
		this.teacherGrade = teacherGrade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOneselfGradeName() {
		return oneselfGradeName;
	}

	public void setOneselfGradeName(String oneselfGradeName) {
		this.oneselfGradeName = oneselfGradeName;
	}

	public String getTeacherGradeName() {
		return teacherGradeName;
	}

	public void setTeacherGradeName(String teacherGradeName) {
		this.teacherGradeName = teacherGradeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
