package com.wfb.utils.util;

import com.jrzmq.core.rest.RestApiFieldAnn;

public class PageModel {
	@RestApiFieldAnn(title = "页数")
    private Integer page = 1;
    
    @RestApiFieldAnn(title = "每页显示数")
    private Integer rows = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
    
    
}
