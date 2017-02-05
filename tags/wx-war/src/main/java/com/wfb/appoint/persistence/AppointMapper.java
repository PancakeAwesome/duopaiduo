package com.wfb.appoint.persistence;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.appoint.model.AppointModel;
import com.wfb.appoint.model.req.AppointReqVo;
import com.wfb.utils.IConverter;

public interface AppointMapper extends IConverter<AppointModel> {
	/**
	 * 保存网点
	 * @param AppointModel
	 * @return
	 */
	int insert(AppointModel AppointModel);
	
	PageList<AppointModel> getAppoint(AppointReqVo vo, PageBounds pageBounds);
	
	AppointModel getAppointModel(AppointReqVo vo);
}
