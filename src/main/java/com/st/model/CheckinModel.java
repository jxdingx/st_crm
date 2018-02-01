package com.st.model;

import com.core.model.BaseModel;

public class CheckinModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String onecheck;
	private String twocheck;
	private String threecheck;
	private String fourcheck;
	private String fivecheck;
	private String sixcheck;
	private String time;
	private Integer score;

	private String startTime;
	private String endTime;

	private String onecheckName;
	private String twocheckName;
	private String threecheckName;
	private String fourcheckName;
	private String fivecheckName;
	private String sixcheckName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOnecheck() {
		return onecheck;
	}

	public void setOnecheck(String onecheck) {
		this.onecheck = onecheck;
	}

	public String getTwocheck() {
		return twocheck;
	}

	public void setTwocheck(String twocheck) {
		this.twocheck = twocheck;
	}

	public String getThreecheck() {
		return threecheck;
	}

	public void setThreecheck(String threecheck) {
		this.threecheck = threecheck;
	}

	public String getFourcheck() {
		return fourcheck;
	}

	public void setFourcheck(String fourcheck) {
		this.fourcheck = fourcheck;
	}

	public String getFivecheck() {
		return fivecheck;
	}

	public void setFivecheck(String fivecheck) {
		this.fivecheck = fivecheck;
	}

	public String getSixcheck() {
		return sixcheck;
	}

	public void setSixcheck(String sixcheck) {
		this.sixcheck = sixcheck;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOnecheckName() {
		return onecheckName;
	}

	public void setOnecheckName(String onecheckName) {
		this.onecheckName = onecheckName;
	}

	public String getTwocheckName() {
		return twocheckName;
	}

	public void setTwocheckName(String twocheckName) {
		this.twocheckName = twocheckName;
	}

	public String getThreecheckName() {
		return threecheckName;
	}

	public void setThreecheckName(String threecheckName) {
		this.threecheckName = threecheckName;
	}

	public String getFourcheckName() {
		return fourcheckName;
	}

	public void setFourcheckName(String fourcheckName) {
		this.fourcheckName = fourcheckName;
	}

	public String getFivecheckName() {
		return fivecheckName;
	}

	public void setFivecheckName(String fivecheckName) {
		this.fivecheckName = fivecheckName;
	}

	public String getSixcheckName() {
		return sixcheckName;
	}

	public void setSixcheckName(String sixcheckName) {
		this.sixcheckName = sixcheckName;
	}

}
