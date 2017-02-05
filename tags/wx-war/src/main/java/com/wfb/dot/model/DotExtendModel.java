package com.wfb.dot.model;

public class DotExtendModel {
	private Integer id;
	private Integer dotId;
	private String time;
	private Integer plan;
	
	
	public Integer getPlan() {
		return plan;
	}
	public void setPlan(Integer plan) {
		this.plan = plan;
	}
	public Integer getDotId() {
		return dotId;
	}
	public void setDotId(Integer dotId) {
		this.dotId = dotId;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "DotExtendModel [id=" + id + ", dotId=" + dotId + ", time=" + time + ", plan=" + plan + "]";
	}
}
