package com.wfb.dot.service;

import java.util.List;

import com.wfb.dot.model.DotExtendModel;

public interface DotExtendService {
	/**
	 * 保存网点时间
	 * @param time
	 * @return
	 */
	public void saveDotTime(DotExtendModel dotExtendModel);
	
	List<DotExtendModel> getDotExtendModel(Integer id);
}
