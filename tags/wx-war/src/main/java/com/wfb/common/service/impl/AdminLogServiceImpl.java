package com.wfb.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.common.model.AdminLogModel;
import com.wfb.common.persistence.AdminLogMapper;
import com.wfb.common.service.IAdminLogService;

@Service
public class AdminLogServiceImpl implements IAdminLogService {

	@Resource
	private AdminLogMapper adminLogMapper;
	
	@Override
	public void saveAdminLog(AdminLogModel vo) {
		adminLogMapper.saveAdminLog(vo);
	}
	
	@Override
	public PageList<AdminLogModel> getAdminLogs(AdminLogModel vo) {
		PageBounds pageBounds = new PageBounds(vo.getPage(), vo.getRows());
		return adminLogMapper.getAdminLogs(vo, pageBounds);
	}
}
