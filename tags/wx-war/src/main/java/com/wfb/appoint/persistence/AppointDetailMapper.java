package com.wfb.appoint.persistence;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.appoint.model.AppointDetailModel;
import com.wfb.appoint.model.req.AppointDetailReqVo;
import com.wfb.utils.IConverter;

public interface AppointDetailMapper extends IConverter<AppointDetailModel> {
	//查询
	PageList<AppointDetailModel> searchAppointDetail(AppointDetailReqVo vo, PageBounds pageBounds);
}
