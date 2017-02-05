package com.wfb.dot.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.dot.model.DotModel;
import com.wfb.dot.model.req.DotReqVo;
import com.wfb.utils.IConverter;

public interface DotMapper extends IConverter<DotModel> {
	/**
	 * 保存网点
	 * @param dotModel
	 * @return
	 */
	int insert(DotModel dotModel);
	
	PageList<DotModel> getDot(DotReqVo vo, PageBounds pageBounds);
	
	List<DotModel> getAllDot(DotReqVo vo);
	
	DotModel getDotModelById(Integer dotId);
	
	List<DotModel> getDotByCityName(@Param("cityName") String cityName);
	
	List<DotModel> getDotByCityCode(@Param("code") String code);
	
}
