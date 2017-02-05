package com.wfb.common.persistence;

import java.util.List;
import com.wfb.common.model.DomesticArea;

public interface DomesticAreaMapper {
	/**
	 * deleteById from table t_domestic_area.
	 */
	int deleteById(Integer fId);

	/**
	 * insert from table t_domestic_area.
	 */
	int insert(DomesticArea record);

	/**
	 * selectById from table t_domestic_area.
	 */
	DomesticArea selectById(Integer fId);

	/**
	 * updateById from table t_domestic_area.
	 */
	int updateById(DomesticArea record);

	List<DomesticArea> selectByPid(Integer pid);
	
	int getCodeByName(String areaName);
}
