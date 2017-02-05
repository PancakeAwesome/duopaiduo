package com.wfb.common.persistence;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.common.model.AdminLogModel;
import com.wfb.utils.IConverter;

public interface AdminLogMapper extends IConverter<AdminLogModel> {

	PageList<AdminLogModel> getAdminLogs(AdminLogModel vo, PageBounds pageBounds);
	
	void saveAdminLog(AdminLogModel vo);
	
}
