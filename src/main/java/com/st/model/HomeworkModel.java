package com.st.model;

import com.core.model.BaseModel;

public class HomeworkModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer classId;
	private Integer teacherId;
	private String title;
	private String scoreStandards;
	private String time;
	private String type;
	
	
	private String typeName;
	private String teacherName;
	private String className;

	private HomeStaModel homeStaModel;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScoreStandards() {
		return scoreStandards;
	}

	public void setScoreStandards(String scoreStandards) {
		this.scoreStandards = scoreStandards;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HomeStaModel getHomeStaModel() {
		return homeStaModel;
	}

	public void setHomeStaModel(HomeStaModel homeStaModel) {
		this.homeStaModel = homeStaModel;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
