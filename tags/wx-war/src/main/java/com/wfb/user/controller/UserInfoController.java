package com.wfb.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jrzmq.core.exception.ErrorCodeConstant;
import com.jrzmq.core.utils.StringUtil;
import com.jrzmq.core.web.annotation.RequestJson;
import com.jrzmq.core.web.converter.Result;
import com.wfb.user.model.AdminModel;
import com.wfb.user.model.RoleModel;
import com.wfb.user.model.User;
import com.wfb.user.model.req.AdminSearchReq;
import com.wfb.user.model.req.LoginReq;
import com.wfb.user.service.IUserService;
import com.wordnik.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/webapp/user/user-info")
public class UserInfoController{

	@Resource
	private IUserService userService;
	private final static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	@ApiOperation(value = "后台用户登录", httpMethod = "POST", response = Result.class, notes = "telephone(手机号, String, Y)<br>password(密码, String, Y)")
	@ResponseBody
	public Result<AdminModel> adminLogin(@RequestJson @Valid LoginReq vo, BindingResult bindingResult) {

		logger.info("AppointController adminLogin method param [ telephone: " + vo.getTelephone() + " ]");
		Result<AdminModel> result = new Result<AdminModel>(null);
		AdminModel adminModel = new AdminModel();

		adminModel = userService.adminUserLogin(vo.getTelephone());
		if(null == adminModel){
			result.fail(ErrorCodeConstant.CHECK_ERROR_CODE, "不存在此管理员信息!");
			return result;
		}
		if("2".equals(adminModel.getIsDel())){
			result.fail(ErrorCodeConstant.CHECK_ERROR_CODE, "此管理员已被删除信息!");
			return result;
		}
		
		result.setData(adminModel);
		return result;
	}
	
	
	@RequestMapping(value = "/getUserDetail/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "用户详情", httpMethod = "GET", response = Result.class, notes = "adminId(管理员ID,Integer,Y)")
	@ResponseBody
	public Result<User> getUserDetail(@PathVariable Integer userId) {
		logger.info("AppointController getUserDetail method start...userId = " + userId);
		Result<User> result = new Result<User>(null);
		result.setData(userService.getUserDetail(userId));
		return result;
	}
	
	@RequestMapping(value = "/getAdmins", method = RequestMethod.POST)
	@ApiOperation(value = "管理员列表信息", httpMethod = "POST", response = Result.class, notes = "page(页数,Interger,N)<br>rows(每页显示数,Interger,N)<br>condition(查询条件,String,N)")
	@ResponseBody
	public Result<List<AdminModel>> getAdmins(@RequestJson @Valid AdminSearchReq vo) {
		logger.info("TestController getAdmins method start...");
		Result<List<AdminModel>> result = new Result<List<AdminModel>>(null);
		result.setData(userService.getAdmins(vo));
		return result;
	}
	
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@ApiOperation(value = "保存角色", httpMethod = "POST", response = Result.class, notes = "")
	@ResponseBody
	public Result<Boolean> saveRole(RoleModel model) {
		logger.info("TestController saveRole method start...");
		Result<Boolean> result = new Result<Boolean>(true);
		userService.saveRole(model);
		return result;
	}

	@RequestMapping(value = "/getRoles", method = RequestMethod.POST)
	@ApiOperation(value = "获取所有角色", httpMethod = "POST", response = Result.class, notes = "")
	@ResponseBody
	public Result<List<RoleModel>> getRoles(@RequestJson @Valid RoleModel model, BindingResult bindingResult) {
		logger.info("TestController getRoles method start...");
		Result<List<RoleModel>> result = new Result<List<RoleModel>>(null);
		result.setData(userService.getRoles(model));
		return result;
	}

	@RequestMapping(value = "/getRole/{roleId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据ID获取角色", httpMethod = "GET", response = Result.class, notes = "")
	@ResponseBody
	public Result<RoleModel> getRole(@PathVariable Integer roleId) {
		logger.info("TestController getRole method start...");
		Result<RoleModel> result = new Result<RoleModel>(null);
		result.setData(userService.getRoleById(roleId));
		return result;
	}

	@RequestMapping(value = "/delRole/{roleId}", method = RequestMethod.GET)
	@ApiOperation(value = "根据ID删除角色", httpMethod = "GET", response = Result.class, notes = "")
	@ResponseBody
	public Result<Boolean> delRole(@PathVariable Integer roleId) {
		logger.info("TestController delRole method start...");
		Result<Boolean> result = new Result<Boolean>(true);
		userService.removeRoleById(roleId);
		return result;
	}
	
	@RequestMapping(value = "/getByAdmin/{admin}", method = RequestMethod.GET)
	@ApiOperation(value = "校验管理员是否存在", httpMethod = "GET", response = Result.class, notes = "admin(管理员登录帐号,String,Y)")
	@ResponseBody
	public Result<Boolean> getByAdmin(@PathVariable String admin) {
		logger.info("TestController getFranchiseeDetail method start...admin = " + admin);
		Result<Boolean> result = new Result<Boolean>(true);
		int isExist = userService.getByAdmin(admin);
		if (isExist > 0) {
			result.fail(ErrorCodeConstant.CHECK_ERROR_CODE, "已存在此帐号！");
			return result;
		}
		return result;
	}
}
