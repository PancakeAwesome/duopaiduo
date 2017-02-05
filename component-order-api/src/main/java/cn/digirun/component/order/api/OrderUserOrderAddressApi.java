package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.SaveUserOrderAddressReq;
import cn.digirun.component.order.api.bean.UpdIsDefUserOrderAddressReq;
import cn.digirun.component.order.model.UserOrderAddress;
import cn.digirun.component.order.service.manager.IUserOrderAddressManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderUserOrderAddressApi 
 * @Description: 订单_用户地址 
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order_UserOrderAddress")
public class OrderUserOrderAddressApi extends BasicApi{
	
	@Autowired
	private IUserOrderAddressManager userOrderAddressManager;
	
	/**
	 * 获取用户所有地址
	 * @param userlogisticsReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getUserOrderAddresss")
	public Ret<List<UserOrderAddress>> getUserOrderAddresss(Long userId) {
		
	    return Ret.success(userOrderAddressManager.getUserOrderAddresss(userId));
	}
	
	/**
	 * 根据ID获取用户地址
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getUserOrderAddressById")
	public Ret<UserOrderAddress> getUserOrderAddressById(Long id) {
		return Ret.success(userOrderAddressManager.getUserOrderAddressById(id));
	}
	
	/**
	 * 保存用户地址
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveUserOrderAddress")
	public Ret<Boolean> saveUserOrderAddress(@ApiBody SaveUserOrderAddressReq req) {
		UserOrderAddress vo = new UserOrderAddress();
		BeanUtils.copyProperties(req, vo);
		userOrderAddressManager.saveUserOrderAddress(vo);
		return Ret.success(true);
	}
	
	/**
	 * 删除用户地址
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/delUserOrderAddressById")
	public Ret<Boolean> delUserOrderAddressById(Long id) {
		userOrderAddressManager.delUserOrderAddressById(id);
		return Ret.success(true);
	}
	
	/**
	 * 修改默认地址
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/updIsDef")
	public Ret<Boolean> updIsDef(@ApiBody UpdIsDefUserOrderAddressReq req) {
		UserOrderAddress vo = new UserOrderAddress();
		BeanUtils.copyProperties(req, vo);
		userOrderAddressManager.updIsDef(vo);
		return Ret.success(true);
	}
	
	
}
