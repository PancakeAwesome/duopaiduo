package com.wfb.utils.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jrzmq.core.rest.RestClient;
import com.jrzmq.core.rest.RestException;
import com.jrzmq.core.utils.JacksonUtil;
import com.wfb.utils.model.AdminLogModel;
import com.wfb.utils.model.AdminSearchReq;
import com.wfb.utils.model.AppointReq;
import com.wfb.utils.model.DotReq;
import com.wfb.utils.model.LoginReq;
import com.wfb.utils.model.RoleModel;
import com.wfb.utils.model.vo.AppointReqVo;
import com.wfb.utils.model.vo.DotReqVo;


@Controller
@RequestMapping("/webapp/utils")
public class UtilsController {

	private final static Logger logger = Logger.getLogger(UtilsController.class);

	private String baseUrl = "http://localhost:8080/wx-war/webapp/";
	private String userUrl = baseUrl + "user/user-info/";
	private String dotUrl = baseUrl + "dot/dot-info/";
	private String appointUrl = baseUrl + "appoint/appoint-info/";
	private String commonUrl = baseUrl + "common/common-info/";
	

	@RequestMapping("/adminLogin")
	@ResponseBody
	public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("UtilsController adminLogin start...");
		String adminLoginUrl = userUrl + "adminLogin";
		LoginReq vo = new LoginReq();
		vo.setTelephone(request.getParameter("username"));
		vo.setPassword(request.getParameter("password"));
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(adminLoginUrl, "POST", json);
		
		
		String result = restClient.execute();
        
        String userId = JSONObject.parseObject(JSONObject.parseObject(result).getString("data")).getString("adminId");
        String adminName = JSONObject.parseObject(JSONObject.parseObject(result).getString("data")).getString("adminName");
        String position = JSONObject.parseObject(JSONObject.parseObject(result).getString("data")).getString("roleName");
        String roles = JSONObject.parseObject(JSONObject.parseObject(result).getString("data")).getString("roles");
        
        request.getSession().setAttribute("userId", userId);
        request.getSession().setAttribute("userName", adminName);
        request.getSession().setAttribute("position", position);
        request.getSession().setAttribute("roles", ","+roles+",");
		
		return result;
	}
	
	
	@RequestMapping("/saveDot")
	@ResponseBody
	public void saveDot(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String saveDotUrl = dotUrl + "saveDot";
		DotReq vo = new DotReq();
		vo.setCityName(request.getParameter("cityName"));
		vo.setDotName(request.getParameter("dotName"));
		vo.setAddress(request.getParameter("address"));
		vo.setTelephone(null == request.getParameter("telephone") ? 0 :Long.parseLong(request.getParameter("telephone").toString()));
		String [] time = new String[4];
		StringBuffer sf1 = new StringBuffer("");
		StringBuffer sf2 = new StringBuffer("");
		StringBuffer sf3 = new StringBuffer("");
		StringBuffer sf4 = new StringBuffer("");
		sf1.append(request.getParameter("hour1")).append(":");
		sf1.append(request.getParameter("min1")).append("-");
		sf1.append(request.getParameter("hour2")).append(":");
		sf1.append(request.getParameter("min2"));
		time[0] = sf1.toString();
		
		sf2.append(request.getParameter("hour3")).append(":");
		sf2.append(request.getParameter("min3")).append("-");
		sf2.append(request.getParameter("hour4")).append(":");
		sf2.append(request.getParameter("min4"));
		time[1] = sf2.toString();
		
		sf3.append(request.getParameter("hour5")).append(":");
		sf3.append(request.getParameter("min5")).append("-");
		sf3.append(request.getParameter("hour6")).append(":");
		sf3.append(request.getParameter("min6"));
		time[2] = sf3.toString();
		
		sf4.append(request.getParameter("hour7")).append(":");
		sf4.append(request.getParameter("min7")).append("-");
		sf4.append(request.getParameter("hour8")).append(":");
		sf4.append(request.getParameter("min8"));
		time[3] = sf4.toString();
		
		vo.setTime(time);
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(saveDotUrl, "POST", json);
		restClient.execute();
	}
	
	@RequestMapping("/saveAppoint")
	@ResponseBody
	public void saveAppoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String saveAppoint = appointUrl + "saveAppoint";
		AppointReq vo = new AppointReq();
		vo.setDotId(Integer.parseInt(request.getParameter("dotId")));
		vo.setCurrentDate(new Date());
		vo.setAppointDay(Integer.parseInt(request.getParameter("appointDay")));
		vo.setIsCancel(Integer.parseInt(request.getParameter("isCancel")));
		vo.setPersonNum(Integer.parseInt(request.getParameter("personNum")));
		
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(saveAppoint, "POST", json);
		restClient.execute();
	}
	
	@RequestMapping(value = "/searchDot", method = RequestMethod.GET)
	@ResponseBody
	public String searchDot(DotReqVo vo) throws RestException {
		String searchDotUrl = dotUrl + "searchDot";
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(searchDotUrl, "POST", json);
		return restClient.execute();
	}
	
	@RequestMapping(value = "/searchAppoint", method = RequestMethod.GET)
	@ResponseBody
	public String searchAppoint(AppointReqVo vo) throws RestException {
		String searchAppointUrl = appointUrl + "searchAppoint";
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(searchAppointUrl, "POST", json);
		return restClient.execute();
	}
	
	@RequestMapping(value = "/searchAllDot", method = RequestMethod.GET)
	@ResponseBody
	public String searchAllDot(DotReqVo vo) throws RestException {
		String searchAllDotUrl = dotUrl + "searchAllDot";
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(searchAllDotUrl, "POST", json);
		return restClient.execute();
	}
	
	@RequestMapping(value = "/getAdmins", method = RequestMethod.POST)
	@ResponseBody
	public String getAdmins(AdminSearchReq vo) throws RestException {
		String getAdminsUrl = userUrl + "/getAdmins";
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(getAdminsUrl, "POST", json);
		return restClient.execute();
	}
	
	@RequestMapping(value = "/getRoles", method = RequestMethod.POST)
	@ResponseBody
	public String getRolesUrl(RoleModel vo) throws RestException {
		String getRolesUrl = userUrl + "getRoles";
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(getRolesUrl, "POST", json);
		return restClient.execute();
	}
	
	@RequestMapping(value = "/getAdminLogs", method = RequestMethod.POST)
	@ResponseBody
	public String getAdminLogs(AdminLogModel vo) throws RestException {
		logger.info("UtilsController getAdminLogs start...");
		String getAdminLogsUrl = commonUrl + "/getAdminLogs";
		String json = JacksonUtil.obj2Str(vo);
		RestClient restClient = new RestClient(getAdminLogsUrl, "POST", json);
		return restClient.execute();
	}
	
	

}
