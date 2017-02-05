package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.SaveInvoiceTypeReq;
import cn.digirun.component.order.model.InvoiceType;
import cn.digirun.component.order.service.manager.IInvoiceTypeManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderInvoiceTypeApi 
 * @Description: 订单_发票类型维护
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order_InvoiceType")
public class OrderInvoiceTypeApi extends BasicApi{
	
	@Autowired
	private IInvoiceTypeManager invoiceTypeManager;
	
	/**
	 * 获取所有发票类型
	 * @param userlogisticsReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getInvoiceTypes")
	public Ret<List<InvoiceType>> getInvoiceTypes() {
		
	    return Ret.success(invoiceTypeManager.getInvoiceTypes());
	}
	
	/**
	 * 根据ID获取发票类型
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getInvoiceTypeById")
	public Ret<InvoiceType> getInvoiceTypeById(Long id) {
		return Ret.success(invoiceTypeManager.getInvoiceTypeById(id));
	}
	
	/**
	 * 保存发票类型
	 * @param InvoiceTypeReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveInvoiceType")
	public Ret<Boolean> saveInvoiceType(@ApiBody SaveInvoiceTypeReq req) {
		InvoiceType vo = new InvoiceType();
		BeanUtils.copyProperties(req, vo);
		invoiceTypeManager.saveInvoiceType(vo);
		return Ret.success(true);
	}
	
	/**
	 * 删除发票类型
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/delInvoiceTypeById")
	public Ret<Boolean> delInvoiceTypeById(Long id) {
		invoiceTypeManager.delInvoiceTypeById(id);
		return Ret.success(true);
	}
}
