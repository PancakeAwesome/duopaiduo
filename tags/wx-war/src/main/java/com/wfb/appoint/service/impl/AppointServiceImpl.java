package com.wfb.appoint.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.appoint.model.AppointDetailModel;
import com.wfb.appoint.model.AppointModel;
import com.wfb.appoint.model.req.AppointDetailReqVo;
import com.wfb.appoint.model.req.AppointReqVo;
import com.wfb.appoint.persistence.AppointDetailMapper;
import com.wfb.appoint.persistence.AppointMapper;
import com.wfb.appoint.service.AppointService;


@Service
public class AppointServiceImpl implements AppointService {
	@Resource
	private AppointMapper appointMapper;
	
	@Resource
	private AppointDetailMapper appointDetailMapper;

	@Override
	public int saveAppoint(AppointModel appointModel) {
		int row = appointMapper.insert(appointModel);
		int id = 0;
		if (row == 1){
			id = appointModel.getId();
		}
		return id;
	}

	@Override
	public PageList<AppointModel> getAppoint(AppointReqVo vo) {
		PageBounds pageBounds = new PageBounds(vo.getPage(), vo.getRows());
		return appointMapper.getAppoint(vo, pageBounds);
	}

	@Override
	public AppointModel getAppointModel(Integer dotId) {
		AppointReqVo vo = new AppointReqVo();
		vo.setDotId(dotId);
		return appointMapper.getAppointModel(vo);
	}

	@Override
	public PageList<AppointDetailModel> searchAppointDetail(
			AppointDetailReqVo vo) {
		PageBounds pageBounds = new PageBounds(vo.getPage(), vo.getRows());
		return appointDetailMapper.searchAppointDetail(vo, pageBounds);
	}
}
