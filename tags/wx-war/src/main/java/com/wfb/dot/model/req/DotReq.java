package com.wfb.dot.model.req;

public class DotReq {
	private String cityName;
	private String address;
	private String dotName;
	private Long telephone;
	private String [] time;
	
	
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
	
	public String[] getTime() {
		return time;
	}
	
	public void setTime(String[] time) {
		this.time = time;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	
	
}
