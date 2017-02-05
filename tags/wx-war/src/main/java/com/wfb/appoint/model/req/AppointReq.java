package com.wfb.appoint.model.req;

import java.util.Date;

public class AppointReq {
	private Integer dotId;
	private String dotName;
	private Integer personNum;
	private Integer appointDay;
	private Date currentDate;
	private Integer isCancel;
	
	public Integer getDotId() {
		return dotId;
	}

	public void setDotId(Integer dotId) {
		this.dotId = dotId;
	}

	public String getDotName() {
		return dotName;
	}
	
	public void setDotName(String dotName) {
		this.dotName = dotName;
	}
	
	public Integer getPersonNum() {
		return personNum;
	}
	
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	
	public Integer getAppointDay() {
		return appointDay;
	}
	
	public void setAppointDay(Integer appointDay) {
		this.appointDay = appointDay;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}
	
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	public Integer getIsCancel() {
		return isCancel;
	}
	
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
}
