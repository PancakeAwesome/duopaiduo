package com.wfb.appoint.model.req;

import com.wfb.utils.PageModel;

public class AppointReqVo extends PageModel {
	private Integer dotId;
	private String dotName;

	public String getDotName() {
		return dotName;
	}

	public void setDotName(String dotName) {
		this.dotName = dotName;
	}

	public Integer getDotId() {
		return dotId;
	}

	public void setDotId(Integer dotId) {
		this.dotId = dotId;
	}
	
	
	
}
