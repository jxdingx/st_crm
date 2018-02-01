package com.st.model;

import com.core.model.BaseModel;

public class ProjectModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String projectName;
	private Integer comScore;
	private Integer stuScore;
	private Integer teacherScore;
	private String grade;
	private String size;

	private String className;
	private String userName;
	private String gradeName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getComScore() {
		return comScore;
	}

	public void setComScore(Integer comScore) {
		this.comScore = comScore;
	}

	public Integer getStuScore() {
		return stuScore;
	}

	public void setStuScore(Integer stuScore) {
		this.stuScore = stuScore;
	}

	public Integer getTeacherScore() {
		return teacherScore;
	}

	public void setTeacherScore(Integer teacherScore) {
		this.teacherScore = teacherScore;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}
