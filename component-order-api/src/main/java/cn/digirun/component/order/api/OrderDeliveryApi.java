package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.SaveDeliveryReq;
import cn.digirun.component.order.model.Delivery;
import cn.digirun.component.order.service.manager.IDeliveryManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderDeliveryApi 
 * @Description: 订单_物流公司维护
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order_delivery")
public class OrderDeliveryApi extends BasicApi{
	
	@Autowired
	private IDeliveryManager deliveryManager;
	
	/**
	 * 获取所有物流公司
	 * @param userlogisticsReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getDeliverys")
	public Ret<List<Delivery>> getDeliverys() {
		
	    return Ret.success(deliveryManager.getDeliverys());
	}
	
	/**
	 * 根据ID获取物流公司
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getDeliveryById")
	public Ret<Delivery> getDeliveryById(Long id) {
		return Ret.success(deliveryManager.getDeliveryById(id));
	}
	
	/**
	 * 保存物流公司
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveDelivery")
	public Ret<Boolean> saveDelivery(@ApiBody SaveDeliveryReq req) {
		Delivery vo = new Delivery();
		BeanUtils.copyProperties(req, vo);
		deliveryManager.saveDelivery(vo);
		return Ret.success(true);
	}
	
	/**
	 * 删除物流公司
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/delDeliveryById")
	public Ret<Boolean> delDeliveryById(Long id) {
		deliveryManager.delDeliveryById(id);
		return Ret.success(true);
	}
}
