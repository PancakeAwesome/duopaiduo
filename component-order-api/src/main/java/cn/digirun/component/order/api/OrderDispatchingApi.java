package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.SaveDispatchingReq;
import cn.digirun.component.order.model.Dispatching;
import cn.digirun.component.order.service.manager.IDispatchingManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderDispatchingApi 
 * @Description: 订单_支付类型维护
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order_dispatching")
public class OrderDispatchingApi extends BasicApi{
	
	@Autowired
	private IDispatchingManager dispatchingManager;
	
	/**
	 * 获取所有支付类型
	 * @param userlogisticsReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getDispatchings")
	public Ret<List<Dispatching>> getDispatchings() {
		
	    return Ret.success(dispatchingManager.getDispatchings());
	}
	
	/**
	 * 根据ID获取支付类型
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getDispatchingById")
	public Ret<Dispatching> getDispatchingById(Long id) {
		return Ret.success(dispatchingManager.getDispatchingById(id));
	}
	
	/**
	 * 保存支付类型
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveDispatching")
	public Ret<Boolean> saveDispatching(@ApiBody SaveDispatchingReq req) {
		Dispatching vo = new Dispatching();
		BeanUtils.copyProperties(req, vo);
		dispatchingManager.saveDispatching(vo);
		return Ret.success(true);
	}
	
	/**
	 * 删除支付类型
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/delDispatchingById")
	public Ret<Boolean> delDispatchingById(Long id) {
		dispatchingManager.delDispatchingById(id);
		return Ret.success(true);
	}
}
