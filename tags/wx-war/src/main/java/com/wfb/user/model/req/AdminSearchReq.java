package com.wfb.user.model.req;

import com.wfb.utils.PageModel;

public class AdminSearchReq extends PageModel {

	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
