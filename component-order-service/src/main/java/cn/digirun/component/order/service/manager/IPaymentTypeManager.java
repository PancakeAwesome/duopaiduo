package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.PaymentType;

/**
 * 支付方式Serv
 * @author Administrator
 *
 */
public interface IPaymentTypeManager {
	
	/**
	 * 获取所有支付方式
	 * @param userlogisticsReq
	 * @return
	 */
	List<PaymentType> getPaymentTypes();
	
	/**
	 * 根据ID获取支付方式
	 * @param id
	 * @return
	 */
	PaymentType getPaymentTypeById(Long id);
	
	/**
	 * 保存支付方式
	 * @param PaymentTypeReq
	 * @return
	 */
	public void savePaymentType(PaymentType vo);
	
	/**
	 * 删除支付方式
	 * @param id
	 * @return
	 */
	void delPaymentTypeById(Long id);
	
}
