package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.InvoiceType;

/**
 * 发票类型Serv
 * @author Administrator
 *
 */
public interface IInvoiceTypeManager {
	
	/**
	 * 获取所有发票类型
	 * @param userlogisticsReq
	 * @return
	 */
	List<InvoiceType> getInvoiceTypes();
	
	/**
	 * 根据ID获取发票类型
	 * @param id
	 * @return
	 */
	InvoiceType getInvoiceTypeById(Long id);
	
	/**
	 * 保存发票类型
	 * @param InvoiceTypeReq
	 * @return
	 */
	public void saveInvoiceType(InvoiceType vo);
	
	/**
	 * 删除发票类型
	 * @param id
	 * @return
	 */
	void delInvoiceTypeById(Long id);
	
}
