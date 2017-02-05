package com.wfb.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wfb.common.model.DomesticArea;
import com.wfb.common.persistence.DomesticAreaMapper;
import com.wfb.common.service.IDomesticAreaService;

@Service
public class DomesticAreaServiceImpl implements IDomesticAreaService {

	@Resource
	private DomesticAreaMapper domesticAreaMapper;

	@Override
	public List<DomesticArea> getDoesticAreaList(Integer parentId) {
		return domesticAreaMapper.selectByPid(parentId);
	}

	@Override
	public DomesticArea findById(Integer id) {
		return domesticAreaMapper.selectById(id);
	}
}
