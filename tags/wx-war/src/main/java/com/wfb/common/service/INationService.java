package com.wfb.common.service;

import java.util.List;

import com.wfb.common.model.Nation;

public interface INationService {
	/**
	 * 获取民族列表
	 * @return
	 */
	public List<Nation> getList();
}
