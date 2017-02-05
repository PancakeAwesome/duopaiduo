package com.wfb.dot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.jrzmq.core.web.annotation.RequestJson;
import com.jrzmq.core.web.converter.Result;
import com.wfb.appoint.model.AppointModel;
import com.wfb.appoint.service.AppointService;
import com.wfb.dot.model.DotExtendModel;
import com.wfb.dot.model.DotModel;
import com.wfb.dot.model.req.DotReq;
import com.wfb.dot.model.req.DotReqVo;
import com.wfb.dot.service.DotExtendService;
import com.wfb.dot.service.DotService;
import com.wordnik.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/webapp/dot/dot-info/")
public class DotController{

	@Resource
	private DotService dotService;
	
	@Resource
	private DotExtendService dotExtendService;
	
	@Resource
	private AppointService appointService;
	//private final static Logger logger = LoggerFactory.getLogger(AppointController.class);
	
	@RequestMapping(value = "/saveDot", method = RequestMethod.POST)
	@ApiOperation(value = "保存网点", httpMethod = "POST", response = Result.class, notes = "cityName(城市名, String, Y)<br>dotName(网点名称, String, Y)")
	@ResponseBody
	public Result<String> saveDot(@RequestJson @Valid DotReq vo, BindingResult bindingResult) {
		Result<String> result = new Result<String>(null);
		DotModel dotModel = new DotModel();
		dotModel.setCityName(vo.getCityName());
		dotModel.setDotName(vo.getDotName());
		dotModel.setAddress(vo.getAddress());
		dotModel.setTelephone(vo.getTelephone());
		//保存网点并返回id
		int dotId = dotService.saveDot(dotModel);
		//保存时间段
		for(String str : vo.getTime()){
			DotExtendModel dotExtendModel = new DotExtendModel();
			dotExtendModel.setDotId(dotId);
			dotExtendModel.setTime(str);
			dotExtendService.saveDotTime(dotExtendModel);
		}
		result.setMsg("操作成功");
		return result;
	}
	
	@RequestMapping(value = "/searchDot", method = RequestMethod.POST)
	@ApiOperation(value = "查詢网点", httpMethod = "POST", response = Result.class, notes = "")
	@ResponseBody
	public Result<List<DotModel>> searchDot(@RequestJson @Valid DotReqVo vo) {
		Result<List<DotModel>> result = new Result<List<DotModel>>(null);
		PageList<DotModel> list = dotService.getDot(vo);
		for (DotModel dotModel : list){
			List<DotExtendModel> extendList = dotExtendService.getDotExtendModel(dotModel.getId());
			StringBuffer sf = new StringBuffer("");
			for (int i = 0; i < extendList.size(); i++){
				DotExtendModel dotExtendModel =  extendList.get(i);
				if (i == extendList.size() - 1){
					sf.append(dotExtendModel.getTime());
				}else{
					sf.append(dotExtendModel.getTime());
					sf.append(",");
				}
			}
//			dotModel.setTime(sf.toString());
		}
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "/searchAllDot", method = RequestMethod.POST)
	@ApiOperation(value = "查詢所有网点", httpMethod = "POST", response = Result.class, notes = "")
	@ResponseBody
	public Result<List<DotModel>> searchAllDot(@RequestJson @Valid DotReqVo vo) {
		Result<List<DotModel>> result = new Result<List<DotModel>>(null);
		List<DotModel> list = dotService.getAllDot(vo);
		for (DotModel dotModel : list){
			List<DotExtendModel> extendList = dotExtendService.getDotExtendModel(dotModel.getId());
			StringBuffer sf = new StringBuffer("");
			for (int i = 0; i < extendList.size(); i++){
				DotExtendModel dotExtendModel =  extendList.get(i);
				if (i == extendList.size() - 1){
					sf.append(dotExtendModel.getTime());
				}else{
					sf.append(dotExtendModel.getTime());
					sf.append(",");
				}
			}
//			dotModel.setTime(sf.toString());
		}
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "/searchDotByCityName", method = RequestMethod.POST)
	@ApiOperation(value = "根据城市名称,查詢所有网点", httpMethod = "POST", response = Result.class, notes = "")
	@ResponseBody
	public Result<List<DotModel>> searchDotByCityName(DotReqVo vo) {
		Result<List<DotModel>> result = new Result<List<DotModel>>(null);
		List<DotModel> list = dotService.getDotByCityName(vo);
		for (DotModel dotModel : list){
			List<DotExtendModel> extendList = dotExtendService.getDotExtendModel(dotModel.getId());
			StringBuffer sf = new StringBuffer("");
			for (int i = 0; i < extendList.size(); i++){
				DotExtendModel dotExtendModel =  extendList.get(i);
				if (i == extendList.size() - 1){
					sf.append(dotExtendModel.getTime());
				}else{
					sf.append(dotExtendModel.getTime());
					sf.append(",");
				}
			}
//			dotModel.setTime(sf.toString());
			AppointModel appointModel = appointService.getAppointModel(dotModel.getId());
			int day = appointModel.getAppointDay();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> dateList = new ArrayList<String>();
			for (int i = 1; i <=day; i++){
				Calendar instance = Calendar.getInstance();
				instance.add(instance.DAY_OF_MONTH, i);
				String dateStr = sdf.format(instance.getTime());
				dateList.add(dateStr);
			}
			dotModel.setDateList(dateList);
			//预约状态
			List<Integer> stateList = new ArrayList<Integer>();
			stateList.add(appointModel.getIsCancel());
			dotModel.setStateList(stateList);
		}
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "/dotList", method = RequestMethod.GET)
	public ModelAndView dotList(@RequestParam(value="code",required=false,defaultValue="nj") String code) throws Exception {
		List<DotModel> list = dotService.getDotByCityCode(code);
		for (DotModel dotModel : list){
			List<DotExtendModel> extendList = dotExtendService.getDotExtendModel(dotModel.getId());
//			StringBuffer sf = new StringBuffer("");
//			for (int i = 0; i < extendList.size(); i++){
//				DotExtendModel dotExtendModel =  extendList.get(i);
//				if (i == extendList.size() - 1){
//					sf.append(dotExtendModel.getTime());
//				}else{
//					sf.append(dotExtendModel.getTime());
//					sf.append(",");
//				}
//			}
			dotModel.setTime(extendList);
			//AppointModel appointModel = appointService.getAppointModel(dotModel.getId());
			int day = dotModel.getAppointDay();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> dateList = new ArrayList<String>();
			for (int i = 1; i <=day; i++){
				Calendar instance = Calendar.getInstance();
				instance.add(instance.DAY_OF_MONTH, i);
				String dateStr = sdf.format(instance.getTime());
				dateList.add(dateStr);
			}
			dotModel.setDateList(dateList);
			//预约状态
			
			List<Integer> stateList = new ArrayList<Integer>();
			stateList.add(dotModel.getIsCancel());
			 
			dotModel.setStateList(stateList);
		}
		
		
		list.forEach(dm->{
			System.out.println(dm);
		});
		ModelAndView mav = new ModelAndView();
		mav.addObject("dotModelList", list);
		mav.setViewName("office");
		
		return mav;
	}
	
}
