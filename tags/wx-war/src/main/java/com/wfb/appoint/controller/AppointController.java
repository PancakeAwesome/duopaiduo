package com.wfb.appoint.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.jrzmq.core.web.annotation.RequestJson;
import com.jrzmq.core.web.converter.Result;
import com.wfb.appoint.model.AppointDetailModel;
import com.wfb.appoint.model.AppointModel;
import com.wfb.appoint.model.req.AppointDetailReqVo;
import com.wfb.appoint.model.req.AppointReq;
import com.wfb.appoint.model.req.AppointReqVo;
import com.wfb.appoint.service.AppointService;
import com.wfb.common.controller.CommonController;
import com.wfb.dot.model.DotModel;
import com.wfb.dot.persistence.DotMapper;
import com.wordnik.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/webapp/appoint/appoint-info/")
public class AppointController{
	 private final static Logger logger = LoggerFactory.getLogger(AppointController.class);
	@Resource
	private AppointService appointService;
	
	@Resource
	private DotMapper dotMapper;
	
	//private final static Logger logger = LoggerFactory.getLogger(AppointController.class);
	
	@RequestMapping(value = "/saveAppoint", method = RequestMethod.POST)
	@ApiOperation(value = "保存预约", httpMethod = "POST", response = Result.class, notes = "cityName(城市名, String, Y)<br>AppointName(网点名称, String, Y)")
	@ResponseBody
	public Result<String> saveAppoint(@RequestJson @Valid AppointReq vo, BindingResult bindingResult) {
		Result<String> result = new Result<String>(null);
		AppointModel appointModel = new AppointModel();
		DotModel dotModel = dotMapper.getDotModelById(vo.getDotId());
		appointModel.setDotId(vo.getDotId());
		appointModel.setDotName(dotModel.getDotName());
		appointModel.setAppointDay(vo.getAppointDay());
		appointModel.setCurrentDate(new Date());
		appointModel.setIsCancel(vo.getIsCancel());
		appointModel.setPersonNum(vo.getPersonNum());
		//保存预约并返回id
		int appointId = appointService.saveAppoint(appointModel);
		if (appointId > 0){
			result.setMsg("操作成功");
		}
		logger.info(appointId+"");
		return result;
	}
	
	@RequestMapping(value = "/searchAppoint", method = RequestMethod.POST)
	@ApiOperation(value = "查詢预约", httpMethod = "POST", response = Result.class, notes = "")
	@ResponseBody
	public Result<List<AppointModel>> searchAppoint(@RequestJson @Valid AppointReqVo vo) {
		Result<List<AppointModel>> result = new Result<List<AppointModel>>(null);
		PageList<AppointModel> list = appointService.getAppoint(vo);
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "/sendAppoint", method = RequestMethod.POST, name = "提交预约地点")
	public ModelAndView sendAddress(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dotId = null == request.getParameter("appointAddress") ? "" : request
				.getParameter("appointAddress");
		DotModel dotModel = dotMapper.getDotModelById(Integer.parseInt(dotId));
		String appointDate = null == request.getParameter("appointDate") ? "" : request
				.getParameter("appointDate");
		String appointTime = null == request.getParameter("appointTime") ? "" : request
				.getParameter("appointTime");
		request.getSession().setAttribute("dotId", dotId);
		request.getSession().setAttribute("appointAddress", dotModel.getDotName());
		request.getSession().setAttribute("appointDate", appointDate);
		request.getSession().setAttribute("appointTime", appointTime);
		return new ModelAndView("redirect:/office_xq.jsp");
	}
	
	@RequestMapping(value = "/searchAppointDetail/{idCard}", method = RequestMethod.GET)
	@ApiOperation(value = "根据身份证查詢预约", httpMethod = "GET", response = Result.class, notes = "")
	@ResponseBody
	public Result<List<AppointDetailModel>> searchAppointDetail(@PathVariable String idCard) {
		Result<List<AppointDetailModel>> result = new Result<List<AppointDetailModel>>(null);
		AppointDetailReqVo vo = new AppointDetailReqVo();
		vo.setIdCard(idCard);
		PageList<AppointDetailModel> list = appointService.searchAppointDetail(vo);
		result.setData(list);
		return result;
	}
	
	
}
