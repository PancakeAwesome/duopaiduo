package com.wfb.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wfb.common.model.AdminLogModel;
import com.wfb.common.persistence.AdminLogMapper;
import com.wfb.user.model.AdminModel;
import com.wfb.user.model.RoleModel;
import com.wfb.user.model.User;
import com.wfb.user.model.req.AdminSearchReq;
import com.wfb.user.persistence.AdminMapper;
import com.wfb.user.persistence.RoleMapper;
import com.wfb.user.persistence.UserMapper;
import com.wfb.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private AdminMapper adminMapper;
	
	@Resource
	private AdminLogMapper adminLogMapper;
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	public User getUserDetail(Integer userId) {
		return userMapper.getUserDetail(userId);
	}

	@Override
	public AdminModel adminUserLogin(String admin) {
		
		AdminModel adminModel = adminMapper.adminUserLogin(admin);
		if(null != adminModel){
			AdminLogModel logModel = new AdminLogModel();
			logModel.setAdminId(adminModel.getAdminId());
			logModel.setEvent("后台用户登录！");
			adminLogMapper.saveAdminLog(logModel);
		}
		return adminModel;
	}

	@Override
	public PageList<AdminModel> getAdmins(AdminSearchReq vo) {
		PageBounds pageBounds = new PageBounds(vo.getPage(), vo.getRows());
		return adminMapper.getAdmins(vo, pageBounds);
	}
	
	@Override
	public PageList<RoleModel> getRoles(RoleModel vo) {
		PageBounds pageBounds = new PageBounds(vo.getPage(), vo.getRows());
		PageList<RoleModel> datas = roleMapper.getPageList(vo, pageBounds);
		String[] actions;
		for (RoleModel roleModel : datas) {
			actions = roleModel.getActionIds().split(",");
			String actionName = "";
			for (String actionId : actions) {
				actionName += roleMapper.getActionName(Integer.parseInt(actionId)) + "、";
			}
			roleModel.setActionName(actionName.substring(0, actionName.lastIndexOf("、")));
		}
		return datas;
	}
	
	@Override
	public int getByAdmin(String admin) {
		return adminMapper.getByAdmin(admin);
	}
	
	@Override
	public void saveRole(RoleModel vo) {

		if (null == vo.getRoleId()) {
			roleMapper.save(vo);
		} else {
			roleMapper.update(vo);
		}
	}
	
	@Override
	public RoleModel getRoleById(Integer roleId) {
		return roleMapper.getById(roleId);
	}

	@Override
	public void removeRoleById(Integer roleId) {
		roleMapper.remove(roleId);
	}
}
