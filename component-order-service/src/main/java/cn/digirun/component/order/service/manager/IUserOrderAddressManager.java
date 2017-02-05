package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.UserOrderAddress;

/**
 * 用户订单地址Serv
 * @author Administrator
 *
 */
public interface IUserOrderAddressManager {
	
	/**
	 * 获取用户所有订单地址
	 * @return
	 */
	List<UserOrderAddress> getUserOrderAddresss(Long userId);
	
	/**
	 * 根据ID获取用户订单地址
	 * @param id
	 * @return
	 */
	UserOrderAddress getUserOrderAddressById(Long id);
	
	/**
	 * 保存用户订单地址
	 * @param UserOrderAddressReq
	 * @return
	 */
	public void saveUserOrderAddress(UserOrderAddress vo);
	
	/**
	 * 修改默认地址
	 * @param UserOrderAddressReq
	 * @return
	 */
	public void updIsDef(UserOrderAddress vo);
	
	/**
	 * 删除用户订单地址
	 * @param id
	 * @return
	 */
	void delUserOrderAddressById(Long id);
	
}
