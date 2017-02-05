package com.wfb.dot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wfb.dot.model.DotExtendModel;
import com.wfb.dot.persistence.DotExtendMapper;
import com.wfb.dot.service.DotExtendService;

@Service
public class DotExtendServiceImpl implements DotExtendService {

	@Resource
	private DotExtendMapper dotExtendMapper;
	
	@Override
	public void saveDotTime(DotExtendModel dotExtendModel) {
		dotExtendMapper.saveDotTime(dotExtendModel);
	}

	@Override
	public List<DotExtendModel> getDotExtendModel(Integer id) {
		return dotExtendMapper.getDotExtendModel(id);
	}

}
