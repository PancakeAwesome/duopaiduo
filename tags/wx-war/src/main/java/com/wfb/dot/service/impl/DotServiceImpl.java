package com.wfb.dot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.dot.model.DotModel;
import com.wfb.dot.model.req.DotReqVo;
import com.wfb.dot.persistence.DotMapper;
import com.wfb.dot.service.DotService;

@Service
public class DotServiceImpl implements DotService {
	@Resource
	private DotMapper dotMapper;

	@Override
	public int saveDot(DotModel dotModel) {
		int row = dotMapper.insert(dotModel);
		int id  = 0;
		if (row == 1){
			id = dotModel.getId();
		}
		return id;
	}

	@Override
	public PageList<DotModel> getDot(DotReqVo vo) {
		PageBounds pageBounds = new PageBounds(vo.getPage(), vo.getRows());
		return dotMapper.getDot(vo, pageBounds);
	}

	@Override
	public List<DotModel> getAllDot(DotReqVo vo) {
		return dotMapper.getAllDot(vo);
	}

	@Override
	public List<DotModel> getDotByCityName(DotReqVo vo) {
		return dotMapper.getDotByCityName(vo.getCityName());
	}
	
	@Override
	public List<DotModel> getDotByCityCode(String code) {
		return dotMapper.getDotByCityCode(code);
	}
}
