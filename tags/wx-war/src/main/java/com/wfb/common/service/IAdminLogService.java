package com.wfb.common.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.common.model.AdminLogModel;

public interface IAdminLogService {

	PageList<AdminLogModel> getAdminLogs(AdminLogModel vo);
	
	void saveAdminLog(AdminLogModel vo);
}
