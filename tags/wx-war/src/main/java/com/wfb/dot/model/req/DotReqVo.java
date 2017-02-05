package com.wfb.dot.model.req;

import com.wfb.utils.PageModel;

public class DotReqVo extends PageModel {
	private String dotName;
	private String cityName;
	private String code;

	public String getDotName() {
		return dotName;
	}

	public void setDotName(String dotName) {
		this.dotName = dotName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
}
