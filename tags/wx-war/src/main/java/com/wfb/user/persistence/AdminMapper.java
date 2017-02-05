package com.wfb.user.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.user.model.AdminModel;
import com.wfb.user.model.req.AdminSearchReq;
import com.wfb.utils.IConverter;

public interface AdminMapper extends IConverter<AdminModel> {

	int insert(AdminModel model);

	AdminModel getAdminDetail(Integer adminId);
	
	AdminModel adminUserLogin(@Param("admin") String admin);
	
	int getByAdmin(@Param("admin") String admin);
	
	PageList<AdminModel> getAdmins(AdminSearchReq vo, PageBounds pageBounds);
	
	String getIdByAdmin(@Param("admins") List<String> admins, @Param("adminType") String adminType);
	
	String getFranUserIds();
	
	String getLogisUserIds();
}
