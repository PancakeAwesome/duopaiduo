package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import cn.digirun.component.order.model.AreaModel;
import cn.digirun.component.order.service.manager.IAreaManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: AreaApi 
 * @Description: 地区api
 * @author 管超
 *  
 */
@RestController
@RequestMapping("/order")
public class AreaApi extends BasicApi{
	
	@Autowired
	private IAreaManager areaManager;

	/**
	 * 获取地点信息
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getAreas")
	public Ret<List<AreaModel>> getOrderDetail(Integer id) {
		return Ret.success(areaManager.getAreas(id));
	}
	
}
