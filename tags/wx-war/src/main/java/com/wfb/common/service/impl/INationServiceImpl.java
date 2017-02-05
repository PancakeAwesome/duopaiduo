package com.wfb.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wfb.common.model.Nation;
import com.wfb.common.persistence.NationMapper;
import com.wfb.common.service.INationService;

@Service
public class INationServiceImpl implements INationService {

	@Resource
	private NationMapper nationMapper;
	
	@Override
	public List<Nation> getList() {
		return nationMapper.getList();
	}

}
