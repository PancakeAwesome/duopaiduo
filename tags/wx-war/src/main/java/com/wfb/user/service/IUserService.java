package com.wfb.user.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.user.model.AdminModel;
import com.wfb.user.model.RoleModel;
import com.wfb.user.model.User;
import com.wfb.user.model.req.AdminSearchReq;

public interface IUserService {
	User getUserDetail(Integer userId);
	AdminModel adminUserLogin(String admin);
	PageList<AdminModel> getAdmins(AdminSearchReq vo);
	PageList<RoleModel> getRoles(RoleModel vo);
	int getByAdmin(String admin);
	void saveRole(RoleModel vo);
	RoleModel getRoleById(Integer roleId);
	void removeRoleById(Integer roleId);
}
