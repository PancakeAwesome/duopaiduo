package com.wfb.appoint.service;


import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.appoint.model.AppointDetailModel;
import com.wfb.appoint.model.AppointModel;
import com.wfb.appoint.model.req.AppointDetailReqVo;
import com.wfb.appoint.model.req.AppointReqVo;

public interface AppointService {
	/**
	 * 保存网点
	 * @param AppointModel
	 * @return
	 */
	public int saveAppoint(AppointModel AppointModel);
	
	public PageList<AppointModel> getAppoint(AppointReqVo vo);
	
	public PageList<AppointDetailModel> searchAppointDetail(AppointDetailReqVo vo);
	
	public AppointModel getAppointModel(Integer dotId);
}
