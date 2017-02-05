package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.SavePaymentTypeReq;
import cn.digirun.component.order.model.PaymentType;
import cn.digirun.component.order.service.manager.IPaymentTypeManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderPaymentTypeApi 
 * @Description: 订单_支付方式维护
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order_paymentType")
public class OrderPaymentTypeApi extends BasicApi{
	
	@Autowired
	private IPaymentTypeManager paymentTypeManager;
	
	/**
	 * 获取所有支付方式
	 * @param userlogisticsReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getPaymentTypes")
	public Ret<List<PaymentType>> getPaymentTypes() {
		
	    return Ret.success(paymentTypeManager.getPaymentTypes());
	}
	
	/**
	 * 根据ID获取支付方式
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getPaymentTypeById")
	public Ret<PaymentType> getPaymentTypeById(Long id) {
		return Ret.success(paymentTypeManager.getPaymentTypeById(id));
	}
	
	/**
	 * 保存支付方式
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/savePaymentType")
	public Ret<Boolean> savePaymentType(@ApiBody SavePaymentTypeReq req) {
		PaymentType vo = new PaymentType();
		BeanUtils.copyProperties(req, vo);
		paymentTypeManager.savePaymentType(vo);
		return Ret.success(true);
	}
	
	/**
	 * 删除支付方式
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/delPaymentTypeById")
	public Ret<Boolean> delPaymentTypeById(Long id) {
		paymentTypeManager.delPaymentTypeById(id);
		return Ret.success(true);
	}
}
