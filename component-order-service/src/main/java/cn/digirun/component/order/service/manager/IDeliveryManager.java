package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.Delivery;

/**
 * 物流公司Serv
 * @author Administrator
 *
 */
public interface IDeliveryManager {
	
	/**
	 * 获取所有物流公司
	 * @param userlogisticsReq
	 * @return
	 */
	List<Delivery> getDeliverys();
	
	/**
	 * 根据ID获取物流公司
	 * @param id
	 * @return
	 */
	Delivery getDeliveryById(Long id);
	
	/**
	 * 保存物流公司
	 * @param DeliveryReq
	 * @return
	 */
	public void saveDelivery(Delivery vo);
	
	/**
	 * 删除物流公司
	 * @param id
	 * @return
	 */
	void delDeliveryById(Long id);
	
}
