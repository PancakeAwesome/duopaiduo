package com.wfb.dot.model;

import java.util.List;

public class DotModel {
	private Integer id;
	private String cityName;
	private String address;
	private String dotName;
	private Long telephone;
	private Integer isCancel;
	private List<DotExtendModel> time;
	private Integer appointDay;
	private List<String> dateList;
	private List<Integer> stateList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDotName() {
		return dotName;
	}
	public void setDotName(String dotName) {
		this.dotName = dotName;
	}
	public Long getTelephone() {
		return telephone;
	}
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	
	public List<DotExtendModel> getTime() {
		return time;
	}
	public void setTime(List<DotExtendModel> time) {
		this.time = time;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public List<Integer> getStateList() {
		return stateList;
	}
	public void setStateList(List<Integer> stateList) {
		this.stateList = stateList;
	}
	public Integer getAppointDay() {
		return appointDay;
	}
	public void setAppointDay(Integer appointDay) {
		this.appointDay = appointDay;
	}
	@Override
	public String toString() {
		return "DotModel [id=" + id + ", cityName=" + cityName + ", address=" + address + ", dotName=" + dotName
				+ ", telephone=" + telephone + ", isCancel=" + isCancel + ", time=" + time + ", appointDay="
				+ appointDay + ", dateList=" + dateList + ", stateList=" + stateList + "]";
	}
	public Integer getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}
	
	
}
