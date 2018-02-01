package com.st.model;

import com.core.model.BaseModel;

public class AssessmentModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer ranking;
	private Integer score;
	private Integer userId;
	private String userName;
	private String time;
	private Integer checkScore;
	private Integer homeworkScore;
	private Integer projectScore;
	private Integer dailyScore;

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCheckScore() {
		return checkScore;
	}

	public void setCheckScore(Integer checkScore) {
		this.checkScore = checkScore;
	}

	public Integer getHomeworkScore() {
		return homeworkScore;
	}

	public void setHomeworkScore(Integer homeworkScore) {
		this.homeworkScore = homeworkScore;
	}

	public Integer getProjectScore() {
		return projectScore;
	}

	public void setProjectScore(Integer projectScore) {
		this.projectScore = projectScore;
	}

	public Integer getDailyScore() {
		return dailyScore;
	}

	public void setDailyScore(Integer dailyScore) {
		this.dailyScore = dailyScore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
