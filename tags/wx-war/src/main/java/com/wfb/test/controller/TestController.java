package com.wfb.test.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.xalan.lib.Redirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.common.comm.ServiceClient.Request;
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
@RequestMapping("/webapp/test/test1")
public class TestController{

	@Resource
	private IUserService userService;
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ModelAndView test2(HttpServletRequest request,HttpServletResponse rep) {
		request.getSession().setAttribute("test", 1);
		return new ModelAndView("redirect:/test.jsp");
	}
	
	
	
}
