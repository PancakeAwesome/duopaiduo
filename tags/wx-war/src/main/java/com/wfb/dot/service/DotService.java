package com.wfb.dot.service;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.dot.model.DotModel;
import com.wfb.dot.model.req.DotReqVo;

public interface DotService {
	/**
	 * 保存网点
	 * @param dotModel
	 * @return
	 */
	public int saveDot(DotModel dotModel);
	
	public PageList<DotModel> getDot(DotReqVo vo);
	
	public List<DotModel> getAllDot(DotReqVo vo);
	
	public List<DotModel> getDotByCityName(DotReqVo vo);
	
	public List<DotModel> getDotByCityCode(String code);
	
	
	
}
