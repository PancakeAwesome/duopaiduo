package com.wfb.dot.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wfb.dot.model.DotExtendModel;

public interface DotExtendMapper {
	/**
	 * 保存网点时间
	 * @param time
	 * @return
	 */
	public void saveDotTime(DotExtendModel dotExtendModel);
	
	public List<DotExtendModel> getDotExtendModel(Integer dotId);
}
