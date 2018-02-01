package com.st.model;

import com.core.model.BaseModel;

public class UserModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String trueName;
	private String phone;
	private Integer classId;
	private String address;
	private Integer roleId;
	private Integer loginType;

	private String roleName;
	private CheckinModel checkinModel;

	private HomeStaModel homeStaModel;

	private AssessmentModel assessmentModel;

	private QuestionAnswerModel questionAnswerModel;

	private ClassModel classModel;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public CheckinModel getCheckinModel() {
		return checkinModel;
	}

	public void setCheckinModel(CheckinModel checkinModel) {
		this.checkinModel = checkinModel;
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

	public AssessmentModel getAssessmentModel() {
		return assessmentModel;
	}

	public void setAssessmentModel(AssessmentModel assessmentModel) {
		this.assessmentModel = assessmentModel;
	}

	public QuestionAnswerModel getQuestionAnswerModel() {
		return questionAnswerModel;
	}

	public void setQuestionAnswerModel(QuestionAnswerModel questionAnswerModel) {
		this.questionAnswerModel = questionAnswerModel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public ClassModel getClassModel() {
		return classModel;
	}

	public void setClassModel(ClassModel classModel) {
		this.classModel = classModel;
	}

}
