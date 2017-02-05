package com.wfb.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;







import com.jrzmq.core.web.annotation.RequestJson;
import com.jrzmq.core.web.converter.Result;
import com.wfb.common.model.AdminLogModel;
import com.wfb.common.model.DomesticArea;
import com.wfb.common.model.Nation;
import com.wfb.common.service.IAdminLogService;
import com.wfb.common.service.IDomesticAreaService;
import com.wfb.common.service.INationService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/webapp/common/common-info")
public class CommonController{
    
    private final static Logger logger = LoggerFactory.getLogger(CommonController.class);
    
    @Resource
    private IAdminLogService adminLogService;
    @Resource
    private IDomesticAreaService domesticAreaService;
    @Resource
    private INationService nationService;
   
	
    @ApiOperation(value = "工作日志", httpMethod = "POST", response = Result.class, notes = "")
	@RequestMapping(value = "/getAdminLogs", method = RequestMethod.POST)
	@ResponseBody
	public Result<List<AdminLogModel>> getAdminLogs(@RequestJson @Valid AdminLogModel vo){
		logger.info("CommonController getAdminLogs method inner! ");
		Result<List<AdminLogModel>> result = new Result<List<AdminLogModel>>(null);
		result.setData(adminLogService.getAdminLogs(vo));
		return result;
	}
    
    @ApiOperation(value = "获取地区列表", httpMethod = "GET", response = Result.class, notes = "pid(地市编号, Integer, Y)")
    @RequestMapping(value = "/listArea/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<DomesticArea>> list(@PathVariable Integer pid)
    {
        logger.info("CommonController DomesticAreaList method start...param[pid:" + pid +"]");
        Result<List<DomesticArea>> result = new Result<List<DomesticArea>>(domesticAreaService.getDoesticAreaList(pid));
        return result;
    }
    
    @ApiOperation(value = "获取民族", httpMethod = "GET", response = Result.class, notes = "pid(地市编号, Integer, Y)")
    @RequestMapping(value = "/searchMz", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Nation>> searchMz()
    {
        Result<List<Nation>> result = new Result<List<Nation>>(nationService.getList());
        return result;
    }
    
    
	
}
