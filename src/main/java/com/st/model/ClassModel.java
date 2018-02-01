package com.st.model;

import com.core.model.BaseModel;

public class ClassModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String className;
	private String courseName;
	private Integer teacherId;
	private Integer stuNum;

	private String teacherName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getStuNum() {
		return stuNum;
	}

	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
